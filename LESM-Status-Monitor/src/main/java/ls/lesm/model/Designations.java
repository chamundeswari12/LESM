package ls.lesm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class Designations {
	
	@Id
	@GeneratedValue(generator = "desg_gen",strategy = GenerationType.AUTO)
	private Integer desgId;
	
	@Column(length=30)
	private String desgNames;
	
	@JsonIgnore
	private Date createdAt;//timpStamp
	
	@JsonIgnore
	@Column(length=30)
	private String createdBy;//principal
	
	@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="supervisor_id")
	private Designations designations;
	
}
