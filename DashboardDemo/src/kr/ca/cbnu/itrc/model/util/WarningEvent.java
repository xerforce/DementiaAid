/**
 * 
 */
package kr.ca.cbnu.itrc.model.util;

import java.util.EventObject;

/**
 * @author Jerry
 *
 */
public class WarningEvent extends EventObject{
	private Object obj;

	public WarningEvent(Object source) {
		super(source);
		obj=source;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object getSource() {
		// TODO Auto-generated method stub
		return super.getSource();
	}

}
