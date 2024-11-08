/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * @author isabelle
 */
public class PurchaseRequisition {
    private final static AtomicInteger COUNT = new AtomicInteger(0);
    private final String requisitionId;
    private String requestorId;
    private final Date requestDate; 
    private Status status;
    private String itemId;
    private int quantityRequested; 
    private String approverId;
    private Date approvalDate;
    private String approvalRemarks;
    
    public enum Status{PENDING, APPROVED, REJECTED};
    
    public PurchaseRequisition(String requestorId, String itemId, int quantityRequested) {
        this.requisitionId = String.format("R%04d", COUNT.incrementAndGet());
        this.requestorId = requestorId;
        this.requestDate = new Date();
        this.status = Status.PENDING;
        this.itemId = itemId;
        this.quantityRequested = quantityRequested;
        this.approverId = null;
        this.approvalDate = null;
        this.approvalRemarks = null;
    }

    public String getRequisitionId() {
        return requisitionId;
    }

    public String getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantityRequested() {
        return quantityRequested;
    }

    public void setQuantityRequested(int quantityRequested) {
        this.quantityRequested = quantityRequested;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }
    
    

}

// which attributes should be automatically set and never changed?
// requisitionId, requestDate
// which attributes should be automatically set and can be changed in the future?
// status, approverId, approvalDate, remarks


// final - cannot be changed
// static - class level variable/method, can be accessed without an object intiated