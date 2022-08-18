package ls.lesm.service;

import org.springframework.web.multipart.MultipartFile;

import ls.lesm.model.Attachment;

public interface AttachmentService {

	Attachment saveAttachment(MultipartFile multipartFile,int emp_id) throws Exception;

	Attachment getAttachment(int fileId) throws Exception;

}
