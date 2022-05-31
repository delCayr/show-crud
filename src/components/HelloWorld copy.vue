<template>
  <div class="test">
    <div class="top">
      <button
        :class="['insert', active == 'insert' ? 'active' : '']"
        @click="change('insert')"
      >
        添加员工
      </button>
      <button
        :class="['delete', active == 'delete' ? 'active' : '']"
        @click="change('delete')"
      >
        删除员工
      </button>
      <button
        :class="['update', active == 'update' ? 'active' : '']"
        @click="change('update')"
      >
        更新员工
      </button>
      <button
        :class="['select', active == 'select' ? 'active' : '']"
        @click="change('select')"
      >
        查询员工
      </button>
    </div>
    <div class="content">
      <div class="insert-cont" v-if="current == 'insert'">
        <h2>添加员工</h2>
        <div class="cont" v-for="(item, index) in stuInfoList" :key="index">
          <div class="key">{{ item.key }}</div>
          <input type="text" v-model="item.value" />
        </div>
        <!-- <button class="clear" @click="clearInfo(stuInfoList)">重置</button> -->
        <button class="addstu" @click="addStu">添加员工</button>
      </div>
      <div class="delete-cont" v-if="current == 'delete'">
        <h2>删除员工</h2>
        <div class="cont">
          <div class="key">请输入删除员工的工号</div>
          <input type="text" v-model="delValue" />
        </div>
        <button class="clear" @click="deleteInfo">删除员工</button>
      </div>
      <div class="update-cont" v-if="current == 'update'">
        <h2>更新员工信息</h2>
        <div class="tip">请查询员工信息后再更新信息</div>
        <div class="cont" v-for="(item, index) in updateInfoList" :key="index">
          <div class="key">{{ item.key }}</div>
          <input type="text" v-model="item.value" />
        </div>
        <!-- <button
          class="clear"
          @click="searchInfo(updateInfoList[1].value, 'get')"
        >
          确认
        </button> -->
        <button class="addstu" @click="updateInfo">修改员工</button>
      </div>
      <div class="select-cont" v-if="current == 'select'">
        <h2>查询员工</h2>
        <div class="cont">
          <div class="key" style="padding-left: 10px; font-size: 16px">
            输入员工工号
          </div>
          <input type="text" v-model="searchSno" />
          <button class="btn" @click="searchInfo(searchSno, 'search')">
            查询员工
          </button>
        </div>
        <div class="cont" v-for="(item, index) in updateInfoList" :key="index">
          <div class="key">{{ item.key }}</div>
          <div>{{ item.value }}</div>
        </div>
        <!--  <table
          align="center"
          width="280"
          height="80"
          border="0"
          cellspacing="1"
          cellpadding="7"
        >
          <thead>
            <tr>
              <th>工号</th>
              <th>姓名</th>
              <th>性别</th>
              <th>年龄</th>
              <th>专业</th>
              thead表格区域 th表格小方框
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
        </table> -->
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
        { key: "员工姓名", value: "", type: "sname" },
        { key: "员工工号", value: "", type: "sno" },
        { key: "员工性别", value: "", type: "ssex" },
        { key: "员工年龄", value: "", type: "sage" },
        { key: "员工职位", value: "", type: "sage" },
        { key: "毕业大学", value: "", type: "sage" },
        { key: "所在专业", value: "", type: "sage" },

        // { key: "员工专业", value: "", type: "sdept" },
      ],
      updateInfoList: [
        { key: "员工姓名", value: "狄玉婷", type: "sname" },
        { key: "员工工号", value: "20204820", type: "sno" },
        { key: "员工性别", value: "女", type: "ssex" },
        { key: "员工年龄", value: "19", type: "sage" },
        { key: "员工职位", value: "后端工程师", type: "sage" },
        { key: "毕业大学", value: "天津理工大学", type: "sage" },
        { key: "所在专业", value: "计算机", type: "sage" },
        // { key: "员工专业", value: "", type: "sdept" },
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
      alert("添加员工成功");
      // let stuInfo = {};
      // for (const item of this.stuInfoList) {
      //   stuInfo[item.type] = item.value;
      // }
      // let { data } = await this.$axios.post("/addInfo", stuInfo);
      // if (data == "添加学生成功") {
      //   alert(data);
      //   this.clearInfo(this.stuInfoList);
      // } else {
      //   alert(data);
      // }
    },
    clearInfo(list) {
      for (const item of list) {
        item.value = "";
      }
    },
    async deleteInfo() {
      let { data } = await this.$axios.get("/deleteInfo?sno=" + this.delValue);
      if (data == "删除学生成功") {
        // alert(data);
        alert("删除员工成功");
        this.clearInfo();
      } else {
        alert(data);
      }
    },
    async updateInfo() {
      alert("员工信息更新成功");
      // let stuInfo = {};
      // for (const item of this.updateInfoList) {
      //   stuInfo[item.type] = item.value;
      // }
      // let { data } = await this.$axios.post("/updateStuInfo", stuInfo);
      // if (data == "更新学生成功") {
      //   // alert(data);
      //   alert("员工信息更新成功");
      //   this.clearInfo(this.updateInfoList);
      // } else {
      //   alert(data);
      // }
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
  height: 150%;
  width: 100%;
  background-image: url("https://img2.baidu.com/it/u=3053345765,2389138219&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=308");
  background-size: cover;
}
.top {
  padding-top: 80px;
  height: 100px;
  width: 550px;
  margin: 0 auto;
  /* margin-bottom: 60px; */
}
.btn {
  height: 30px;
  width: 80px;
  font-size: 14px;
  display: block;
  margin: 0 auto;
}
button {
  margin-left: 25px;
  width: 100px;
  height: 50px;
  font-size: 18px;
  background-color: rgb(175, 159, 181);
  color: white;
  border-radius: 15px;
}
.active {
  background-color: #30a6b9;
}
.content {
  width: 500px;
  height: 600px;
  background-color: #30a6b9;
  margin: 0 auto;
}
.insert-cont,
.delete-cont,
.update-cont {
  background-color: #30a6b9;
  width: 100%;
  height: 100%;
  color: white;
}
.select-cont {
  background-color: #30a6b9;
  width: 100%;
  height: 100%;
  color: white;
  text-align: left;
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
  color: white;
  padding: 20px;
  text-align: center;
  background-color: #30a6b9;
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
  bottom: 10%;
  left: 50%;
  transform: translateX(-50%);
}
</style>
