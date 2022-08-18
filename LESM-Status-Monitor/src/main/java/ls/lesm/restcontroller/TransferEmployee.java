package ls.lesm.restcontroller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.Address;
import ls.lesm.model.Designations;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.User;
import ls.lesm.payload.request.TransferRequest;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.UserRepository;
import ls.lesm.service.impl.TransferEmployeeService;

@RestController
//@RequestMapping("/api/v1/transfer")

public class TransferEmployee {
	
	
	
	@Autowired
	MasterEmployeeDetailsRepository employeeDetailsRepository;
	@Autowired
	UserRepository userRepo;
	@Autowired
	TransferEmployeeService transferEmployeeService;
	
//	@PutMapping("/transfer")
//	public ResponseEntity<?> TansferingAnEmployee()
//	{
//		
//	}
	
	
	
	
	@GetMapping("/getAllEmployeeUnderCL")
	public ResponseEntity<List<MasterEmployeeDetails>> AllEmployeesUnderCL(Principal principal) {
		
		List<MasterEmployeeDetails> K=transferEmployeeService.getAllEmployeeUnderCL(principal);
		
		
		return new ResponseEntity<List<MasterEmployeeDetails>>(K,HttpStatus.OK) ;
		
	}
	
	
	
	@GetMapping("/getAllSameDesignations")
	public ResponseEntity<?> getAllSameDesignations(@RequestParam String LancesoftId,@RequestParam String TransferLId)
	{
		transferEmployeeService.transferService(LancesoftId,TransferLId);
		
		return new ResponseEntity<>("Transfer",HttpStatus.OK) ; 
	}
	
	
	
	@GetMapping("/SameDesignationsDropdown")
	public ResponseEntity<List<MasterEmployeeDetails>> getAllSameDesignations(Principal principal)
	{
		
		
		User user=this.userRepo.findByUsername(principal.getName());
		String lancesoft=user.getUsername();
		MasterEmployeeDetails masterEmployeeDetails =employeeDetailsRepository.findByLancesoft(lancesoft);
		List<MasterEmployeeDetails> M=employeeDetailsRepository.findByDesignations(masterEmployeeDetails.getDesignations());
		return  new ResponseEntity<List<MasterEmployeeDetails>>(M,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/transfer")
	public ResponseEntity<?> transferEmployee(@RequestParam String LancesoftId,
												@RequestParam  String  TansferLancesoftId,
												@RequestBody TransferRequest transferRequest )
	{
		
		transferEmployeeService.transferEmployeeToSameDesignation(LancesoftId,TansferLancesoftId,transferRequest);
		return  new ResponseEntity<>("transfer and update successfully",HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/getAllSameDesignationsMR")
	public ResponseEntity<?> getAllSameDesignationsMR(@RequestParam String LancesoftId,Principal principal)
	{
		transferEmployeeService.transferServiceMR(LancesoftId,principal);
		
		return new ResponseEntity<>("Tr",HttpStatus.OK) ; 
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
