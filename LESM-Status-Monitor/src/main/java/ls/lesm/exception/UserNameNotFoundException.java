package ls.lesm.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserNameNotFoundException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
			public UserNameNotFoundException(String message)
			{
				super(message);
			}
				

}
