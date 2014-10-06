package br.com.sbcuni.util;

import java.io.Serializable;
import java.util.List;

public class RepeatPaginator implements Serializable {

	private static final long serialVersionUID = 7714249614426665052L;
	private static final int NU_REGISTRO_PADRAO = 10;
    private static final int PAGINA_INDICE_PADRAO = 1;

    private int registros;
    private int registrosTotal;
    private int paginaIndice;
    private int paginas;
    private List<?> origModel;
    private List<?> model;

    public RepeatPaginator(List<?> model) {
        this.origModel = model;
        this.registros = NU_REGISTRO_PADRAO;
        this.paginaIndice = PAGINA_INDICE_PADRAO;        
        this.registrosTotal = model.size();

        if (registros > 0) {
            paginas = registros <= 0 ? 1 : registrosTotal / registros;

            if (registrosTotal % registros > 0) {
                paginas++;
            }

            if (paginas == 0) {
                paginas = 1;
            }
        } else {
            registros = 1;
            paginas = 1;
        }

        updateModel();
    }

    public void updateModel() {
        int fromIndex = getPrimeiro();
        int toIndex = getPrimeiro() + registros;

        if(toIndex > this.registrosTotal) {
            toIndex = this.registrosTotal;
        }

        this.model = origModel.subList(fromIndex, toIndex);
    }

    public void proximo() {
        if(this.paginaIndice < paginas) {
            this.paginaIndice++;
        }

        updateModel();
    }
    
    public void ultimo() {
    	this.paginaIndice = paginas;
    	
    	updateModel();
    }
    
    public void primeiro() {
    	this.paginaIndice = 1;
    	
    	updateModel();
    }

    public void anterior() {
        if(this.paginaIndice > 1) {
            this.paginaIndice--;
        }

        updateModel();
    }   

    public int getRegistros() {
        return registros;
    }

    public int getRegistrosTotal() {
        return registrosTotal;
    }

    public int getPaginaIndice() {
        return paginaIndice;
    }

    public int getPaginas() {
        return paginas;
    }

    public final int getPrimeiro() {
        return (paginaIndice * registros) - registros;
    }

    public List<?> getModel() {
        return model;
    }

    public void setPaginaIndice(int paginaIndice) {
        this.paginaIndice = paginaIndice;
    }
	
}
