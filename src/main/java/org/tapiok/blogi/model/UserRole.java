package org.tapiok.blogi.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Tapio
 */
@Entity
public class UserRole implements Serializable {

	private static final long serialVersionUID = -8346255607938415127L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private UserEntity userEntity;
    private Integer userRoleId;

    public Integer getUserRoleId() {
        return userRoleId;
    }

}
