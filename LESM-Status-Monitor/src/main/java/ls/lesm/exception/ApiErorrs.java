package ls.lesm.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErorrs {
	
	public ApiErorrs(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	private Date timeStamp;
	private String errorMessage;
	private String errorCode;
	private String fieldName;

}
