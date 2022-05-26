package com.haley.teastusys.controller;

import com.haley.teastusys.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class addInfo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/addInfo")
    public String getStuInfo(@RequestBody Student param) {
        log.info(param.getSno()+param.getSname()+param.getSsex()+param.getSage());
        String sql = "insert into student values(?,?,?,?,?)";
        int count = jdbcTemplate.update(sql, param.getSno(), param.getSname(), param.getSsex(), param.getSage(), param.getSdept());
        log.info(String.valueOf(count));
        if (count == 1) {
            return "添加学生成功";
        } else {
            return "添加学生失败";
        }
    }

    @GetMapping("/deleteInfo")
    public String delStuInfo(@RequestParam String sno) {
        log.info(sno);
        String sql = "delete from student where Sno=?";
        int count = jdbcTemplate.update(sql, sno);
        if (count == 1) {
            return "删除学生成功";
        } else {
            return "删除学生失败";
        }
    }

    @GetMapping("/searchBySno")
    public List<Map<String, Object>> searchById(@RequestParam String sno) {
        log.info(sno);
        String sql = "select * from student where Sno=?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, sno);
        return maps;
    }
    @PostMapping("/updateStuInfo")
    public String updateStuInfo(@RequestBody Student param){
        String sql = "update  student set Sname=?,Ssex=?,Sage=?,Sdept=? where Sno=?";
        int count = jdbcTemplate.update(sql,  param.getSname(), param.getSsex(), param.getSage(), param.getSdept(),param.getSno());
        if (count == 1) {
            return "更新学生成功";
        } else {
            return "更新学生失败";
        }
    }

}
