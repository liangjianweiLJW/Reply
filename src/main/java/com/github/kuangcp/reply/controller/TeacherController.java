package com.github.kuangcp.reply.controller;

import com.github.kuangcp.reply.dao.TopicDao;
import com.github.kuangcp.reply.domain.SelectTopic;
import com.github.kuangcp.reply.domain.Topic;
import com.github.kuangcp.reply.service.TeacherService;
import com.github.kuangcp.reply.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by https://github.com/kuangcp on 17-12-4  上午10:57
 * 教师业务控制器
 * @author kuangcp
 */
@Slf4j
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    TopicDao topicDao;
    @Autowired
    TopicService topicService;

    @RequestMapping()
    public String teacher(){
        return "teacher/teacher";
    }
    @RequestMapping("/login")
    public String login(){
        return "/teacher/login";
    }
    @RequestMapping("/PublishTopic")
    public String publish(){


        return "/teacher/PublishTopic";
    }
    @RequestMapping("/init")
    public String init(){
        return "teacher/init";
    }

    /**
     * 展示课题
     * @param session
     */
    @RequestMapping("/DealTopic")
    public ModelAndView deal(HttpSession session){
//        long teacherId = (long) session.getAttribute("teacherId");
        long teacherId = 1L;
        List<Topic> topicList = topicService.listTopicByTeacher(teacherId);
        ModelAndView view = new ModelAndView("teacher/DealTopic");

        Map<Long, List<SelectTopic>> studentList = new HashMap<>();
        for(Topic topic:topicList){
            studentList.put(topic.getTopicId(), topicService.listSelectTopicByTopic(topic.getTopicId()));
        }

        view.addObject("topicList", topicList);
        view.addObject("stuList", studentList);
        return view;
//        return "teacher/DealTopic";
    }
    @RequestMapping("/ThesisProposal")
    public String ThesisProposal(){
        return "teacher/ThesisProposal";
    }

    @RequestMapping("/ThesisProposalScore")
    public String ThesisProposalScore(){
        return "teacher/ThesisProposalScore";
    }

    @RequestMapping("/ThesisDefense")
    public String ThesisDefense(){
        return "/teacher/ThesisDefense";
    }
    @RequestMapping("/ThesisDefenseScore")
    public String ThesisDefenseScore(){
        return "/teacher/ThesisDefenseScore";
    }

    // 以上是页面跳转

    @RequestMapping("/addTopic")
    public String addTopic(Topic topic, HttpSession session){

        long teacherId = 1L;
//        long teacherId = (long) session.getAttribute("teacherId");
        log.info(teacherService.saveTopic(topic, teacherId).toString());
        return "redirect:/teacher/PublishTopic";
    }
    @RequestMapping("/editTopic/{topicId}")
    public ModelAndView editTopic(@PathVariable("topicId") Long topicId){
        ModelAndView view = new ModelAndView("/teacher/PublishTopic");
        Topic topic = topicDao.getOne(topicId);
        view.addObject("topicId", topicId);
        view.addObject("name", topic.getName());
        view.addObject("content", topic.getAttention());
        return view;
    }
    @ResponseBody
    @RequestMapping("/del/topic/{topicId}")
    public String deleteTopic(@PathVariable("topicId")Long topicId){
        topicDao.delete(topicId);
        return "1";
    }


}