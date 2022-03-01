package managedBean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.richfaces.component.SortOrder;

import org.richfaces.model.Filter;

@Named("tableSortingBean")
@RequestScoped
public class TableSortingBean implements Serializable {
	 	private static final long serialVersionUID = -6237417487105926855L;
	    private static final String TIMEZONE_GMT_SEPARATOR = "-";
	    private String tableFilter;
	    private String intituleFilter;
		private SortOrder codeUeOrder = SortOrder.unsorted;
	    private SortOrder intituleOrder = SortOrder.unsorted;
	    
	    public void sortByCodeUE() {
	    	intituleOrder = SortOrder.unsorted;
	        if (codeUeOrder.equals(SortOrder.ascending)) {
	        	setCodeUeOrder(SortOrder.descending);
	        } else {
	        	setCodeUeOrder(SortOrder.ascending);
	        }
	    }
	    
	    public void sortByIntitule() {
	    	intituleOrder = SortOrder.unsorted;
	        if (intituleOrder.equals(SortOrder.ascending)) {
	        	setCodeUeOrder(SortOrder.descending);
	        } else {
	        	setCodeUeOrder(SortOrder.ascending);
	        }
	    }
	    
	    public Filter<?> getFilterModuleImpl() {
	        return new Filter<model.Module>() {
	            public boolean accept(model.Module m) {
	                String module = getModuleFilter();
	                if (module == null || module.length() == 0 || module.equals(m.getNom_module())) {
	                    return true;
	                }
	                return false;
	            }
	        };
	    }
	    
	    public Filter<?> getFilterIntituleImpl() {
	        return new Filter<model.Module>() {
	            public boolean accept(model.Module m) {
	                String module = getIntituleFilter();
	                if (module == null || module.length() == 0 || module.equals(m.getIntitule_module())) {
	                    return true;
	                }
	                return false;
	            }
	        };
	    }
	    
	    public SortOrder getCodeUeOrder() {
	        return codeUeOrder;
	    }
	    
	    public SortOrder getIntituleOrder() {
	        return intituleOrder;
	    }
	    
	    public void setCodeUeOrder(SortOrder codeUeOrder) {
	        this.codeUeOrder = codeUeOrder;
	    }
	    
	    public void setIntituleOrder(SortOrder intituleOrder) {
	        this.intituleOrder = intituleOrder;
	    }
	    
	    public String getModuleFilter() {
	        return moduleFilter;
	    }
	 
	    public void setModuleFilter(String moduleFilter) {
	        this.moduleFilter = moduleFilter;
	    }
	    public String getIntituleFilter() {
			return intituleFilter;
		}
		public void setIntituleFilter(String intituleFilter) {
			this.intituleFilter = intituleFilter;
		}

}
