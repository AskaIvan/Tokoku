/*/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pack.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pack.dao.daoToko;
import pack.dao.implementToko;
import pack.model.m_toko;
import pack.view.home;import pack.model.tableModelToko;

/**
 *
 * @author MSI CORE I7-PC
 */
public class controllerToko {
    home hm;
    implementToko impToko;
    List<m_toko> lt;
    public controllerToko(home hm){
        this.hm = hm;
        impToko = new daoToko();
        lt = impToko.getAll();
    }
    public void Reset(){
        hm.getTxtKode().setText("");
        hm.getTxtNama().setText("");
        hm.getTxtHarga().setText("");
        hm.getCbKategori().setSelectedItem(null);
        hm.getCbJenis().setSelectedItem(null);
    }
    public void Hapus(){
        if (!hm.getTxtKode().getText().trim().isEmpty()){
        }
        else{
            String kode = (hm.getTxtKode().getText());
            impToko.HapusData(kode);
            JOptionPane.showMessageDialog(hm, "Data berhasil dihapus");
        }
    }
    public void SimpanData(){
        m_toko toko = new m_toko();
        toko.setkode(hm.getTxtKode().getText());
        toko.setnama(hm.getTxtNama().getText());
        toko.setharga(hm.getTxtHarga().getText());
        toko.setkategori(hm.getCbKategori().getSelectedItem().toString());
        toko.setjenis(hm.getCbJenis().getSelectedItem().toString());
        impToko.SimpanData(toko);
    }
    public void Ubah(){
        m_toko toko = new m_toko();
        toko.setkode(hm.getTxtKode().getText());
        toko.setnama(hm.getTxtNama().getText());
        toko.setkategori(hm.getCbKategori().getSelectedItem().toString());
        toko.setjenis(hm.getCbJenis().getSelectedItem().toString());
        toko.setharga(hm.getTxtHarga().getText());
        impToko.UbahData(toko);
    }
    public void isiTable() {
        lt =impToko.getAll();
        tableModelToko tmt = new tableModelToko(lt);
        hm.getTableData().setModel(tmt);
    }
    public void isiField(int row){
        hm.getTxtKode().setText(lt.get(row).getkode().toString());
        hm.getTxtNama().setText(lt.get(row).getnama().toString());
        hm.getCbKategori().setSelectedItem(lt.get(row).getkategori().toString());
        hm.getCbJenis().setSelectedItem(lt.get(row).getjenis().toString());
        hm.getTxtHarga().setText(lt.get(row).getharga().toString());
    }
    public void CariKategori(){
        if (hm.getCbCariKategori().getSelectedItem().toString().isEmpty()){
            impToko.getCariKategori((String)hm.getCbCariKategori().getSelectedItem());
            isiTableCariKategori();
        }
        else{
            JOptionPane.showMessageDialog(hm, "Silahkan Pilih Kategori");
        }
    }
    private void isiTableCariKategori() {
        lt =impToko.getCariKategori((String) hm.getCbKategori().getSelectedItem());
        tableModelToko tmt = new tableModelToko(lt);
        hm.getTableData().setModel(tmt);
    }
}
