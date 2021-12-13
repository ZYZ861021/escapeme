//package Scheduled;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.service.PersonalTaskService;
//@RestController
//@RequestMapping
//public class Scheduled {
//	
//	@Autowired
//	private PersonalTaskService _personalTaskService;
//	
//	@PostMapping("/over")
//	@Scheduled(cron = "0 0 0 * * *")
//	public String over() {
//		_personalTaskService.delete();
//		return "clear";
//	}
//}
