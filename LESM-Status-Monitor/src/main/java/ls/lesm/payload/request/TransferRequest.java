package ls.lesm.payload.request;

import lombok.Data;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.Salary;
import ls.lesm.model.SubDepartments;
@Data
public class TransferRequest {
	public MasterEmployeeDetails masterEmployeeDetails;
	public Salary salary;
	public SubDepartments subDepartments;

}
