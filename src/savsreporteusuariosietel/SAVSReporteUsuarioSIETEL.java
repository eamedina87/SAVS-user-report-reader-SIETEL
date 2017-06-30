/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package savsreporteusuariosietel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.usermodel.CellType;
import org.xml.sax.SAXException;

/**
 *
 * @author erick.medina
 */
public class SAVSReporteUsuarioSIETEL {
public static final String fileName = "D:\\Documentos\\Inspeccion AVS\\2017-05 ClaroTv\\Usuarios\\SIETEL_detalle abonados.xls";
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidFormatException, OpenXML4JException, SAXException {
         HSSFWorkbook wb = null;
        try {
           
            // TODO code application logic here
            wb = readFile(fileName);
            System.out.println("Data dump:\n");
          
            HSSFSheet sheet = wb.getSheetAt(2);
            int rows = sheet.getPhysicalNumberOfRows();
            ArrayList<Usuario> usuarios = new ArrayList<>();
            //HashMap<String, String> ubicacion_parroquia = new HashMap<>();
            HashMap<String, String> ubicacion_ciudad = new HashMap<>();
       
            for (int r = 1; r < rows; r++) {
                    HSSFRow row = sheet.getRow(r);
                    if (row == null) {
                            continue;
                    }
                                                         
                    Usuario usuario = new Usuario();       
                   
                    /* Formato del documento en Excel
                     * MES - PROVINCIA	CIUDAD	PARROQUIA	JURIDICAS	NATURALES	PREPAGO
                     * */
                     
                    usuario.setMes(row.getCell(0)!=null?row.getCell(0).getStringCellValue():"");
                    String provincia = row.getCell(1)!=null?row.getCell(1).getStringCellValue():"";
                    usuario.setProvincia(provincia);
                    String ciudad = row.getCell(2)!=null?row.getCell(2).getStringCellValue():"";
                    usuario.setCiudad(ciudad);
                    String parroquia = row.getCell(3)!=null?row.getCell(3).getStringCellValue():"";
                    usuario.setParroquia(parroquia);
                    usuario.setJuridica(row.getCell(4)!=null?(int)row.getCell(4).getNumericCellValue():0);
                    usuario.setNatural(row.getCell(5)!=null?(int)row.getCell(5).getNumericCellValue():0);
                    usuario.setPrepago(row.getCell(6)!=null?(int)row.getCell(6).getNumericCellValue():0);
                                        
                    usuarios.add(usuario);
                          
                    ubicacion_ciudad.put(ciudad, provincia);
                    //ubicacion_parroquia.put(parroquia, ciudad);
                    
            }
            
                        
            TreeMap<String, String> mSortedUbicaciones = new TreeMap<>(ubicacion_ciudad);
                       
            Iterator it = mSortedUbicaciones.entrySet().iterator();
            int mContTotal = 0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String mCiudad = (String) pair.getKey();
                String mProvincia = (String) pair.getValue();
                int mContJuridica = 0;
                int mContNatural = 0;
                /*
                TreeMap<String, String> mSortedParroquias = new TreeMap<>(ubicacion_parroquia);   
                Iterator it_parroquias = mSortedParroquias.entrySet().iterator();
                
                while (it_parroquias.hasNext()) {
                    Map.Entry pair_parroquias = (Map.Entry)it_parroquias.next();
                    String mParroquia = (String) pair_parroquias.getKey();
                    String mCiudad2 = (String) pair_parroquias.getValue();
                  */  
                for (Usuario usuario:usuarios){
                    if (!usuario.getCiudad().equals(mCiudad)) continue;
                    mContTotal++;
                    mContJuridica=mContJuridica+usuario.getJuridica();
                    mContNatural=mContNatural+usuario.getNatural();
                                   
                //}
                    
                }
                
                
                
                System.out.println(mProvincia+";"+mCiudad+";"+mContJuridica+";"+mContNatural);
                
                it.remove(); // avoids a ConcurrentModificationException
            }
            
            System.out.println("Total:"+mContTotal);
           
        } catch (IOException ex) {
            Logger.getLogger(SAVSReporteUsuarioSIETEL.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            try {
                wb.close();
            } catch (IOException ex) {
                Logger.getLogger(SAVSReporteUsuarioSIETEL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static HSSFWorkbook readFile(String filename) throws IOException {
	    FileInputStream fis = new FileInputStream(filename);
	    try {
	        return new HSSFWorkbook(fis);		
	    } finally {
	        fis.close();
	    }
	}
}
