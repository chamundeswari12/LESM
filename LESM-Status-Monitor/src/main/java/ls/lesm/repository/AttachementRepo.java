package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.google.common.base.Optional;

import ls.lesm.model.Attachment;

public interface AttachementRepo extends JpaRepository<Attachment, Integer> {
	
	
	Attachment findByMasterEmployeeDetails(int id);
	
	
	@Query("FROM Attachment g where g.masterEmployeeDetails.id = :masterEmployeeDetailsId") //masterEmployeeDetails
    Optional<Attachment>  findBymasterEmployeeDetails_Id(@Param("masterEmployeeDetailsId")int  id);
	
	
	
//	@Query("FROM Attachement g where g.masterEmployeeDetails.id = :masterEmployeeDetails")
//    Optional<Attachment>  findBymasterEmployeeDetails_Id(@Param("masterEmployeeDetails")Integer id);
	
	Attachment findById(int id);
	
	

}
