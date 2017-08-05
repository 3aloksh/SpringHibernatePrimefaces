import java.sql.SQLException;
import java.util.ArrayList;

import com.gadwala.dao.AnswerDao;
import com.gadwala.dao.QuestionDao;
import com.gadwala.dao.ScheduleDao;
import com.gadwala.dao.StudentGroupDao;
import com.gadwala.model.Answer;
import com.gadwala.model.Question;
import com.gadwala.model.StudentGroup;
import com.gadwala.service.AnswerService;
import com.gadwala.service.QuestionService;
import com.gadwala.service.ScheduleService;
import com.gadwala.service.StudentGroupService;


public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		AnswerDao dao = new AnswerService();
		ArrayList<Answer> anss = (ArrayList<Answer>) dao.getAnswersForStudent(24);
		System.out.println(anss.size());
	}

}
