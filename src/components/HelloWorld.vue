<template>
  <div class="test">
    <div class="top">
      <button
        :class="['insert', active == 'insert' ? 'active' : '']"
        @click="change('insert')"
      >
        录入学生信息
      </button>
      <button
        :class="['delete', active == 'delete' ? 'active' : '']"
        @click="change('delete')"
      >
        删除学生信息
      </button>
      <button
        :class="['update', active == 'update' ? 'active' : '']"
        @click="change('update')"
      >
        更新学生信息
      </button>
      <button
        :class="['select', active == 'select' ? 'active' : '']"
        @click="change('select')"
      >
        查询学生信息
      </button>
    </div>
    <div class="content">
      <div class="insert-cont" v-if="current == 'insert'">
        <h2>添加学生信息</h2>
        <div class="cont" v-for="(item, index) in stuInfoList" :key="index">
          <div class="key">{{ item.key }}</div>
          <input type="text" v-model="item.value" />
        </div>
        <button class="clear" @click="clearInfo(stuInfoList)">重置</button>
        <button class="addstu" @click="addStu">添加学生</button>
      </div>
      <div class="delete-cont" v-if="current == 'delete'">
        <h2>删除学生信息</h2>
        <div class="cont">
          <div class="key">请输入要删除学生的学号</div>
          <input type="text" v-model="delValue" />
        </div>
        <button class="clear" @click="deleteInfo">删除学生</button>
      </div>
      <div class="update-cont" v-if="current == 'update'">
        <h2>更新学生信息</h2>
        <div class="tip">输入需要更新学生的学号,点击获取信息将得到该生信息</div>
        <div class="cont" v-for="(item, index) in updateInfoList" :key="index">
          <div class="key">{{ item.key }}</div>
          <input type="text" v-model="item.value" />
        </div>
        <button
          class="clear"
          @click="searchInfo(updateInfoList[1].value, 'get')"
        >
          获取信息
        </button>
        <button class="addstu" @click="updateInfo">修改学生</button>
      </div>
      <div class="select-cont" v-if="current == 'select'">
        <h2>查询学生信息</h2>
        <div class="cont">
          <div class="key">请输入要查询学生的学号</div>
          <input type="text" v-model="searchSno" />
        </div>
        <button class="btn" @click="searchInfo(searchSno, 'search')">
          查询学生
        </button>
        <table
          align="center"
          width="500"
          height="80"
          border="1"
          cellspacing="0"
          cellpadding="8"
        >
          <thead>
            <tr>
              <th>学号</th>
              <th>姓名</th>
              <th>性别</th>
              <th>年龄</th>
              <th>专业</th>
              <!-- thead表格区域 th表格小方框 -->
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ searchedInfo.Sno }}</td>
              <td>{{ searchedInfo.Sname }}</td>
              <td>{{ searchedInfo.Ssex }}</td>
              <td>{{ searchedInfo.Sage }}</td>
              <td>{{ searchedInfo.Sdept }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    return {
      // eslint-disable-next-line no-unused-labels
      text: "",
      searchSno: "",
      current: "insert",
      active: "insert",
      delValue: "",
      stuInfoList: [
        { key: "学生姓名", value: "", type: "sname" },
        { key: "学生学号", value: "", type: "sno" },
        { key: "学生性别", value: "", type: "ssex" },
        { key: "学生年龄", value: "", type: "sage" },
        { key: "学生专业", value: "", type: "sdept" },
      ],
      updateInfoList: [
        { key: "学生姓名", value: "", type: "sname" },
        { key: "学生学号", value: "", type: "sno" },
        { key: "学生性别", value: "", type: "ssex" },
        { key: "学生年龄", value: "", type: "sage" },
        { key: "学生专业", value: "", type: "sdept" },
      ],
      searchedInfo: { sname: "", sno: "", sage: "", sdept: "", ssex: "" },
    };
  },
  methods: {
    change(state) {
      this.current = state;
      this.active = state;
    },
    async addStu() {
      let stuInfo = {};
      for (const item of this.stuInfoList) {
        stuInfo[item.type] = item.value;
      }
      let { data } = await this.$axios.post("/addInfo", stuInfo);
      if (data == "添加学生成功") {
        alert(data);
        this.clearInfo(this.stuInfoList);
      } else {
        alert(data);
      }
    },
    clearInfo(list) {
      for (const item of list) {
        item.value = "";
      }
    },
    async deleteInfo() {
      let { data } = await this.$axios.get("/deleteInfo?sno=" + this.delValue);
      if (data == "添加学生成功") {
        alert(data);
        this.clearInfo();
      } else {
        alert(data);
      }
    },
    async updateInfo() {
      let stuInfo = {};
      for (const item of this.updateInfoList) {
        stuInfo[item.type] = item.value;
      }
      let { data } = await this.$axios.post("/updateStuInfo", stuInfo);
      if (data == "更新学生成功") {
        alert(data);
        this.clearInfo(this.updateInfoList);
      } else {
        alert(data);
      }
    },
    async searchInfo(sno, category) {
      if (sno) {
        let { data } = await this.$axios.get("/searchBySno?sno=" + sno);
        if (data) {
          if (category === "get") {
            this.updateInfoList[0].value = data[0].Sname;
            this.updateInfoList[1].value = data[0].Sno;
            this.updateInfoList[2].value = data[0].Ssex;
            this.updateInfoList[3].value = data[0].Sage;
            this.updateInfoList[4].value = data[0].Sdept;
          } else {
            this.searchedInfo = data[0];
            console.log(this.searchedInfo);
          }
        } else {
          alert(data);
        }
      }
    },
  },
};
</script>

<style scoped>
.test {
  height: 100%;
  width: 100%;
  background-image: url("../assets/code.jpg");
  background-size: cover;
}
.top {
  padding-top: 80px;
  height: 100px;
  width: 800px;
  margin: 0 auto;
  margin-bottom: 60px;
}
.btn {
  height: 45px;
  width: 60%;
  font-size: 18px;
  display: block;
  margin: 0 auto;
}
button {
  width: 200px;
  height: 80px;
  font-size: 26px;
}
.active {
  background-color: rgb(153, 198, 244);
}
.content {
  width: 800px;
  height: 500px;
  background-color: #fff;
  margin: 0 auto;
}
.insert-cont,
.delete-cont,
.select-cont,
.update-cont {
  background-color: rgb(241, 244, 244);
  width: 100%;
  height: 100%;
}
.cont {
  font-size: 18px;
  display: flex;
  height: 40px;
  justify-content: center;
  align-items: center;
  margin-bottom: 15px;
}
.key {
  margin-right: 30px;
}
input {
  width: 250px;
  height: 60%;
  font-size: 16px;
}
.tip {
  text-align: center;
  padding: 0 20px 20px 20px;
}
h2 {
  color: rgb(116, 116, 116);
  padding: 20px;
  text-align: center;
  background-color: #fff;
}
.clear,
.addstu {
  font-size: 16px;
  width: 100px;
  height: 40px;
  float: right;
  margin: 30px 50px 0 0;
}
table {
  position: absolute;
  bottom: 35%;
  left: 50%;
  transform: translateX(-50%);
}
</style>
