/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.remotedata.remotedatada.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "remotedata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remotedata.findAll", query = "SELECT r FROM Remotedata r"),
    @NamedQuery(name = "Remotedata.findById", query = "SELECT r FROM Remotedata r WHERE r.id = :id"),
    @NamedQuery(name = "Remotedata.findByRemotedate", query = "SELECT r FROM Remotedata r WHERE r.remotedate = :remotedate")})
public class Remotedata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "remotedate")
    private String remotedate;

    public Remotedata() {
    }

    public Remotedata(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemotedate() {
        return remotedate;
    }

    public void setRemotedate(String remotedate) {
        this.remotedate = remotedate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remotedata)) {
            return false;
        }
        Remotedata other = (Remotedata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.remotedata.remotedatada.entities.Remotedata[ id=" + id + " ]";
    }
    
}
