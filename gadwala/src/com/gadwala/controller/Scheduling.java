package com.gadwala.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gadwala.dao.CourseDao;
import com.gadwala.dao.GroupDao;
import com.gadwala.dao.LocationDao;
import com.gadwala.dao.ScheduleDao;
import com.gadwala.model.Group;
import com.gadwala.model.Location;
import com.gadwala.model.Schedule;
import com.gadwala.service.CourseService;
import com.gadwala.service.GroupService;
import com.gadwala.service.LocationService;
import com.gadwala.service.ScheduleService;

@WebServlet("/Scheduling")
public class Scheduling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		GroupDao dao = new GroupService();
		LocationDao lDao = new LocationService();
		CourseDao cDao = new CourseService();
		ScheduleDao sDao = new ScheduleService();
		int counter = 0;
		try {
			if(sDao.getAll().size() > 0 ){
				session.setAttribute("sat", sDao.getAllByDay("sat"));
				session.setAttribute("sun", sDao.getAllByDay("sun"));
				session.setAttribute("mon", sDao.getAllByDay("mon"));
				session.setAttribute("tue", sDao.getAllByDay("tue"));
				session.setAttribute("wed", sDao.getAllByDay("wed"));
				session.setAttribute("thu", sDao.getAllByDay("thu"));
				session.setAttribute("schedules", sDao.getAll());
				response.sendRedirect(request.getContextPath() + "/admin/schedule.jsp");
			} else {
				try {
					if(dao.getAll().size() != 32) {
						session.setAttribute("errMSG", "Please Make sure you've Assigned all Level's Groups to All allowed Courses.");
						response.sendRedirect(request.getContextPath() + "/admin/schedule.jsp");
						return;
					}
					// formulate schedules
					ArrayList<Group> groups = dao.getAll();
					
					// setting sat. schedule
					for (Group group : groups) {
						// for sat. if group already assigned continue
						if(sDao.getScheduleByGroupNameAndLevel(group.getLevel(), group.getName()) != null) {
							continue;
						}
						for (Location location : lDao.getAllLocations()) {
							// check to see if course type is applicable with location type.
							if(cDao.getCourseById(group.getCourseid()).getRoomType().equals(location.getType())) {
								// now location is applicable, set it as active.
								lDao.update(location.getId());
								// create schedule for sat.
								Schedule schedule = new Schedule();
								schedule.setCourseid(group.getCourseid());
								schedule.setGroupname(group.getName());
								schedule.setLevel(group.getLevel());
								schedule.setCoursename(cDao.getCourseById(group.getCourseid()).getName());
								schedule.setInstructor(cDao.getCourseById(group.getCourseid()).getInstractor());
								schedule.setLocation(location.getName());
								schedule.setDay(location.getDay());
								if(counter >= 5) {
									counter = 0;
								}
								counter++;
								schedule.setSlot("slot " + counter);
								
								// now save schedule
								sDao.Create(schedule);
								// remove this location form locations as it's now active.
								System.out.println("break");
								break;
							} else {
								continue;
							}
							
							
						}
					}
					// formulate schedule for days other than sat.
					for (Group group : groups) {
						if(sDao.getScheduleByGroupNameAndLevel(group.getLevel(), group.getName(), group.getCourseid()) != null) {
							continue;
						}
						for (Location location : lDao.getAllLocations()) {
							if(cDao.getCourseById(group.getCourseid()).getRoomType().equals(location.getType())) {
								// now location is applicable, set it as active.
								lDao.update(location.getId());
								// create schedule for other days.
								Schedule schedule = new Schedule();
								schedule.setCourseid(group.getCourseid());
								schedule.setGroupname(group.getName());
								schedule.setLevel(group.getLevel());
								schedule.setCoursename(cDao.getCourseById(group.getCourseid()).getName());
								schedule.setInstructor(cDao.getCourseById(group.getCourseid()).getInstractor());
								schedule.setLocation(location.getName());
								schedule.setDay(location.getDay());
								if(counter >= 5) {
									counter = 0;
								}
								counter++;
								schedule.setSlot("slot " + counter);
								// now save schedule
								sDao.Create(schedule);
								// remove this location form locations as it's now active.
								System.out.println("break");
								break;
							} else {
								continue;
							}
						}
					}
					session.setAttribute("sat", sDao.getAllByDay("sat"));
					session.setAttribute("sun", sDao.getAllByDay("sun"));
					session.setAttribute("mon", sDao.getAllByDay("mon"));
					session.setAttribute("tue", sDao.getAllByDay("tue"));
					session.setAttribute("wed", sDao.getAllByDay("wed"));
					session.setAttribute("thu", sDao.getAllByDay("thu"));
					session.setAttribute("schedules", sDao.getAll());
					response.sendRedirect(request.getContextPath() + "/admin/schedule.jsp");
					
				} catch (SQLException e) {
					System.out.println("Scheduling Servlet: " + e.getMessage());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
