/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAO.ScheduleDAO;
import models.Schedule;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import views.TeacherView;

/**
 *
 * @author GreenRain
 */
public class ScheduleController {

    private TeacherView view;
    private List<Schedule> dataList = new ArrayList<>();
    ScheduleDAO schDAO = new ScheduleDAO();

    public ScheduleController() {
    }

    public ScheduleController(TeacherView view) {
        this.view = view;
    }

    public int getMax() {
        int max = schDAO.getMax();
        return max;
    }

    public void showNewData() {
        dataList = schDAO.getAllSchedules();
        showTable();
    }

    public void showTable() {
        DefaultTableModel tableModel = view.getTableSchedule();
        tableModel.setRowCount(0);

        for (Schedule schedule : dataList) {
            tableModel.addRow(new Object[]{
                schedule.getScheduleId(),
                schedule.getTeacherId(),
                schedule.getClassId(),
                schedule.getCourseName(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getDateOfWeek(),
                schedule.getTimeOfDay()
            });
        }
    }

    public void saveSchedule() {
        int teacherId = Integer.parseInt(view.getTeacherId());
        int classId = Integer.parseInt(view.getClassId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String courseName = view.getCourseName();
        String startTime = dateFormat.format(view.getStartTime());
        String endTime = dateFormat.format(view.getEndTime());
        String dateOfWeek = view.getDateOfWeek();
        String timeOfDay = view.getTimeOfDay();

        Schedule schedule = new Schedule();
        schedule.setTeacherId(teacherId);
        schedule.setClassId(classId);
        schedule.setCourseName(courseName);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setDateOfWeek(dateOfWeek);
        schedule.setTimeOfDay(timeOfDay);
        
        schDAO.addSchedule(schedule);

        view.clearSchedule();
        showNewData();
    }

    public void updateSchedule() {
        int scheduleId = Integer.parseInt(view.getScheduleId());
        if (schDAO.isIDExits(scheduleId)) {
            int teacherId = Integer.parseInt(view.getTeacherId());
            int classId = Integer.parseInt(view.getClassId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String courseName = view.getCourseName();
            String startTime = dateFormat.format(view.getStartTime());
            String endTime = dateFormat.format(view.getEndTime());
            String dateOfWeek = view.getDateOfWeek();
            String timeOfDay = view.getTimeOfDay();

            Schedule schedule = new Schedule();
            schedule.setScheduleId(scheduleId);
            schedule.setTeacherId(teacherId);
            schedule.setClassId(classId);
            schedule.setCourseName(courseName);
            schedule.setStartTime(startTime);
            schedule.setEndTime(endTime);
            schedule.setDateOfWeek(dateOfWeek);
            schedule.setTimeOfDay(timeOfDay);

            schDAO.updateSchedule(schedule);

            view.clearSchedule();
            showNewData();
        } else {
            view.showMessage("Schedule id doesn't exists");
        }
    }

    public void deleteSchedule() {
        int scheduleId = Integer.parseInt(view.getScheduleId());

        if (schDAO.isIDExits(scheduleId)) {
            int yesOrNo = view.showConfirmDeleteDialog("Schedule records will also be deleted", "Schedule Delete");
            if (yesOrNo == view.OK_Option()) {
                schDAO.removeSchedule(scheduleId);
            }
            showNewData();
            view.clearStudent();
        } else {
            view.showMessage("Schedule id doesn't exists");
        }
        view.clearSchedule();
        showNewData();
    }

    public void searchScheduleByDate() {
        String searchDate = view.getSearchShedule();
        dataList = schDAO.getScheduleByDate(searchDate);
        showTable();
    }
}
