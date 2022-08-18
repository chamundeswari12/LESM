package ls.lesm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ls.lesm.model.Attachment;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.repository.AttachementRepo;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.service.AttachmentService;
@Service
public class AttachmentServiceImpl  implements AttachmentService{
	
	@Autowired
	MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	@Autowired
	AttachementRepo attachementRepo;

	@Override
	public Attachment saveAttachment(MultipartFile multipartFile,int id) throws Exception {
		

		String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		try {
			if(fileName.contains(".."))
			{
				throw new Exception("filename contains the invalid path sequence");
			}
			Attachment attachment=new Attachment(fileName,multipartFile.getContentType(),multipartFile.getBytes());
			
			  // MasterEmployeeDetails employeeDetails = masterEmployeeDetailsRepository.findByLancesoft(id);
			
		
			// int emp_id=employeeDetails.getEmpId();
			 
			 
			MasterEmployeeDetails masterEmployeeDetails=masterEmployeeDetailsRepository.findById(id).get();
			
			attachment.setMasterEmployeeDetails(masterEmployeeDetails);
			 return attachementRepo.save(attachment);
			
		}
		catch (Exception e) {   
			 throw new Exception("file not saved"+fileName);
		}
		
		
		
	
	}

//	@Override
//	public Attachment getAttachment(int fileId) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Attachment getAttachment(int Id) throws Exception {
//		
//		Attachment attachment=attachementRepo.findById(Id);
//		
//		System.out.println(".............................................................................."+ attachment);
////		MasterEmployeeDetails details=attachment.getMasterEmployeeDetails();
////		System.out.println(".............................................................................."+ details);
////		Integer i=details.getEmpId();
////		System.out.println(".............................................................................."+ i);
//		
////		attachment.get
//		
//		
//		
//		Attachment attachmantEmp=attachementRepo.findByMasterEmployeeDetails(attachment.getMasterEmployeeDetails());
//		System.out.println(".............................................................................."+ attachmantEmp);
//		return attachmantEmp;
//		
		
		
		
		//Attachm attachementRepo.findByMasterEmployeeDetails(Id);
		
		return attachementRepo.findById(Id);
                
              
	}
	
	
	
	
	
	
	
	

}
