/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import DAO.ScheduleDAO;
import models.Schedule;
import javax.swing.table.DefaultTableModel;
import java.sql.Time;
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
    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    public ScheduleController() {
    }
    public ScheduleController(TeacherView view) {
        this.view = view;
    }

    public int getMax() {
        int max = scheduleDAO.getMax();
        return max;
    }

    public void showNewData() {
        dataList = scheduleDAO.getAllSchedules();
        showTable();
    }

    public void showTable() {
        DefaultTableModel tableModel = view.getTable();
        tableModel.setRowCount(0);

        for (Schedule schedule : dataList) {
            tableModel.addRow(new Object[]{
                schedule.getScheduleId(),
                schedule.getTeacherId(),
                schedule.getClassId(),
                schedule.getCourseName(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getDateOfWeek()
            });
        }
    }

    public void saveSchedule() {
        int teacherId = Integer.parseInt(view.getTeacherId());
        int classId = Integer.parseInt(view.getClassId());
        String courseName = view.getCourseName();
        Time startTime = view.getStartTime();
        Time endTime = view.getEndTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfWeek = dateFormat.format(view.getDateOfWeek());

        Schedule schedule = new Schedule(teacherId, classId, courseName, startTime, endTime, dateOfWeek);
        scheduleDAO.addSchedule(schedule);

        view.clearSchedule();
        showNewData();
    }

    public void updateSchedule() {
        int scheduleId = Integer.parseInt(view.getScheduleId());
        int teacherId = Integer.parseInt(view.getTeacherId());
        int classId = Integer.parseInt(view.getClassId());
        String courseName = view.getCourseName();
        Time startTime = view.getStartTime();
        Time endTime = view.getEndTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfWeek = dateFormat.format(view.getDateOfWeek());

        Schedule schedule = new Schedule(scheduleId, teacherId, classId, courseName, startTime, endTime, dateOfWeek);
        scheduleDAO.updateSchedule(schedule);

        view.clearSchedule();
        showNewData();
    }

    public void deleteSchedule() {
        int scheduleId = Integer.parseInt(view.getScheduleId());
        scheduleDAO.removeSchedule(scheduleId);

        view.clearSchedule();
        showNewData();
    }

    public void searchScheduleByDate() {
        String searchDate = view.getSearchShedule();
        dataList = scheduleDAO.getScheduleByDate(searchDate);
        showTable();
    }
}

