package ls.lesm.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JwtRequest {
     @NotBlank(message=" please enter the username")
     @NotNull(message="username should't be null")
	String username;
     @NotBlank(message="please enter the password")
     @NotNull(message="password should't be null")
	String password;
}
