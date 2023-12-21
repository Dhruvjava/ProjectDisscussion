package org.cb.users.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.entity.BaseBO;

@Entity
@Table(name = "permissions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Permissions extends BaseBO {

    private String code; //VIEW -> VIEW code it should not insert data into db

    private String name; // View

}
