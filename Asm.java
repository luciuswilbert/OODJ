package asm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Asm {

    public static void main(String[] args) {
        try {
            purchaseOrder.Read();
//            purchaseOrder po1 = purchaseOrder.getAll_po().get("po0002");
//            System.out.println(po1.getSupplier_id());
            purchaseOrder po1, po2, po3;
            po1 = new purchaseOrder("PR0001", "SUP0001", "PM0001");
            po2 = new purchaseOrder("PR0002", "SUP0002", "PM0001");
            po3 = new purchaseOrder("PR0003", "SUP0001", "PM0001");
              
              //purchaseInvoice pi1=new purchaseInvoice("PI0001","PR0003","SUP0003");  
//            System.out.println(po2.getPr_id());
        } catch (ParseException | IOException ex) {
            Logger.getLogger(Asm.class.getName()).log(Level.SEVERE, null, ex);
        }

        // PurchaseManager_PO p1 = new PurchaseManager_PO();

    }
    
}
