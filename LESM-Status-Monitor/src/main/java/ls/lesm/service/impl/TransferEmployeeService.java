package ls.lesm.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ls.lesm.model.Designations;
import ls.lesm.model.History;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.Salary;
import ls.lesm.model.SubDepartments;
import ls.lesm.model.UpdatedStatus;
import ls.lesm.model.User;
import ls.lesm.payload.request.TransferRequest;
import ls.lesm.repository.HistoryRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.SalaryRepository;
import ls.lesm.repository.UserRepository;

@Service
public class TransferEmployeeService {

	@Autowired
	MasterEmployeeDetailsRepository employeeDetailsRepository;
	@Autowired
	UserRepository userRepo;
	@Autowired
	HistoryRepository historyRepo;
	@Autowired
	SalaryRepository salaryRepository;
	
	
	
	
	

	 
	public void transferService(String LancesoftId,String TransferLid)
	{
		  
       MasterEmployeeDetails employee=this.employeeDetailsRepository.findByLancesoft(LancesoftId);
		
		//System.out.println("?????????????????????????????????????????????"+employee);
		List<MasterEmployeeDetails> employeeDesignation=this.employeeDetailsRepository.findByDesignations(employee.getDesignations());
		 MasterEmployeeDetails tranferingemployee=this.employeeDetailsRepository.findByLancesoft(TransferLid);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+employeeDesignation);
		for (MasterEmployeeDetails currentmasterEmployeeDetails : employeeDesignation)
		{
			
		
		 if(currentmasterEmployeeDetails.getLancesoft().equals(tranferingemployee.getLancesoft()))
		 {
		
			 	List<MasterEmployeeDetails> K = employeeDetailsRepository.findBymasterEmployeeDetails_Id(employee.getEmpId());
		
		
							
							for (MasterEmployeeDetails transfermasterEmployeeDetails : K) 
							{
								
							transfermasterEmployeeDetails.setSupervisor(tranferingemployee);	
							employeeDetailsRepository.save(transfermasterEmployeeDetails);
							}
		
						
		}
		 
		}
	 
		
	}


	public List<MasterEmployeeDetails> getAllEmployeeUnderCL(Principal principal) {
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				User loggedU=this.userRepo.findByUsername(principal.getName());
				//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+loggedU);
				String id=loggedU.getUsername();
				//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+id);
				MasterEmployeeDetails employee=this.employeeDetailsRepository.findByLancesoft(id);
				//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+employee);
				int dbPk=employee.getEmpId();
				//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+dbPk);
				List<MasterEmployeeDetails> K = employeeDetailsRepository.findBymasterEmployeeDetails_Id(dbPk);
				
				//System.out.println("...................................................."+K);
				return K;
		
	}







	public void transferEmployeeToSameDesignation(String lancesoftId, String tansferLancesoftId,
														TransferRequest transferRequest)
	{
		
		
		//Salary sal=new Salary();
		MasterEmployeeDetails trasferEmp =employeeDetailsRepository.findByLancesoft(lancesoftId);
		System.out.println(".............................."+trasferEmp);
		MasterEmployeeDetails trasferToEmp =employeeDetailsRepository.findByLancesoft(tansferLancesoftId);
		
		History historyEmp=new History(trasferEmp.getLancesoft(),trasferEmp.getFirstName(),
											trasferEmp.getLastName(),trasferEmp.getJoiningDate(),
											trasferEmp.getDOB(),trasferEmp.getLocation(),
											trasferEmp.getGender(),trasferEmp.getEmail(),
											trasferEmp.getCreatedAt(),trasferEmp.getVertical(),
											trasferEmp.getStatus(),trasferEmp.getAge(),
											trasferEmp.getIsInternal(),trasferEmp.getPhoneNo(),
										    trasferEmp.getCreatedBy(),trasferEmp.getExitAt(),
										    trasferEmp.getSubDepartments(),trasferEmp.getDepartments(),
											trasferEmp.getDesignations(), trasferEmp.getSupervisor(),
										    trasferEmp.getEmployeeType(), UpdatedStatus.TRANSFER);
				            
		historyRepo.save(historyEmp);	
		
		trasferEmp.setSupervisor(trasferToEmp);	
		
		trasferEmp.setLocation(transferRequest.getMasterEmployeeDetails().getLocation());
		//trasferEmp.setSubDepartments(transferRequest.getSubDepartments());
		Integer i=trasferEmp.getEmpId();
		
		Salary salaryRecord=salaryRepository.findByEmp(trasferEmp.getEmpId());
		
		
		salaryRecord.setSalary(transferRequest.getSalary().getSalary());
		
		
	     Salary s=salaryRepository.save(salaryRecord);
	
		employeeDetailsRepository.save(trasferEmp);	
		
		

	}






///lead transfer
	public void transferServiceMR(String lancesoftId,Principal principal) {
		
//		
//		User loggedU=this.userRepo.findByUsername(principal.getName());
//	
//		String id=loggedU.getUsername();
//	
//		MasterEmployeeDetails currentemployee=this.employeeDetailsRepository.findByLancesoft(id);
//	
//		int dbPk=currentemployee.getEmpId();
//		
//		List<MasterEmployeeDetails> K = employeeDetailsRepository.findBymasterEmployeeDetails_Id(dbPk);
//		for (MasterEmployeeDetails l : K) {
//			System.out.println(l);
//		}

		
		MasterEmployeeDetails employee=this.employeeDetailsRepository.findByLancesoft(lancesoftId);
	
			
					
							Designations desg=employee.getDesignations();
							SubDepartments sub_dep=employee.getSubDepartments();
							MasterEmployeeDetails sup=employee.getSupervisor();
							Integer i=sup.getEmpId();
							List<MasterEmployeeDetails> H=this.employeeDetailsRepository.findByDesignations_IdANDSubDepartmentsANDSupervisor(desg.getDesgId(), sub_dep.getSubDepartId(),i);
							
							for (MasterEmployeeDetails masterEmployeeDetails : H) {
								
								System.out.println(masterEmployeeDetails);
								
							}
						
		
	
	} 

	
	
	
	
}
