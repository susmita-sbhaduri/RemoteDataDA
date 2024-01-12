/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.remotedata.remotedatada.DA;

import javax.persistence.EntityManagerFactory;
import org.bhaduri.remotedata.remotedatada.JPA.RemotedataJpaController;

/**
 *
 * @author sb
 */
public class RemoteDataAccess extends RemotedataJpaController {
    public RemoteDataAccess(EntityManagerFactory emf) {
        super(emf);
    }
    
}
