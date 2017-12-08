/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab5_design.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Артем
 */
@Entity
@Table(name = "employers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employers.findAll", query = "SELECT e FROM Employers e")
    , @NamedQuery(name = "Employers.findById", query = "SELECT e FROM Employers e WHERE e.id = :id")
    , @NamedQuery(name = "Employers.findByName", query = "SELECT e FROM Employers e WHERE e.name = :name")
    , @NamedQuery(name = "Employers.findByPosition", query = "SELECT e FROM Employers e WHERE e.position = :position")})
public class Employers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "position")
    private String position;

    public Employers() {
    }

    public Employers(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Employers)) {
            return false;
        }
        Employers other = (Employers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.lab5_design.domain.Employers[ id=" + id + " ]";
    }
    
}
