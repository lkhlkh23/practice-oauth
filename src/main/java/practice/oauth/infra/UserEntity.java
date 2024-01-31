package practice.oauth.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.oauth.type.UserRoleType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tbl")
public class UserEntity {

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "provdier")
	private String provider;

	@Column(name = "name")
	private String name;

	@Column(name = "picture")
	private String picture;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private UserRoleType userRole;

}
