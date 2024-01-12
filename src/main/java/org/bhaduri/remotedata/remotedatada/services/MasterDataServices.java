/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.remotedata.remotedatada.services;

/**
 *
 * @author sb
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.bhaduri.remotedata.remotedatada.DA.RemoteDataAccess;
import org.bhaduri.remotedata.remotedatada.JPA.exceptions.PreexistingEntityException;
import org.bhaduri.remotedata.remotedatada.entities.Remotedata;
public class MasterDataServices {

    private EntityManagerFactory emf;

    public MasterDataServices() {
        emf = Persistence.createEntityManagerFactory("org.bhaduri.remotedata_RemoteDataDA_jar_1PU");
    }
    
    public void insertIntoRemoteData(int id, String date) {
        RemoteDataAccess remoteDA = new RemoteDataAccess(emf);
        Remotedata remoteDARecord = new Remotedata();
        try { 
            remoteDARecord.setId(id);
            remoteDARecord.setRemotedate(date);
//            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//            Date lastupdateDate = targetFormat.parse(lastCallData.getLastUpdateTime());
//            calltablePK.setLastupdateminute(lastCallData.getLastUpdateTime());

            remoteDA.create(remoteDARecord);
            
//            return HedwigResponseCode.SUCCESS;
        } catch (PreexistingEntityException preexistingEntityException) {
            System.out.println("data exists" + String.valueOf(remoteDARecord.getId())+ remoteDARecord.getRemotedate());
        } //        catch (ParseException ex) {
        //            System.out.println("data parsing problem" + lastCallData.getLastUpdateTime());
        //        }
        catch (Exception exception) {
            System.out.println(exception + " has occurred in saveSripData.");
        }
    }
}
