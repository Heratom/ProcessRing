/**
 * 
 */
package main;

/**
 * @author mflamcou
 *
 */
public class Ack {

	private int IdSender;
	private Long IdMessage;
	
	public Ack(Long IDM, int IDS) {
		this.setIdMessage(IDM);
		this.setIdSender(IDS);
	}

	/**
	 * @return the idMessage
	 */
	public Long getIdMessage() {
		return IdMessage;
	}

	/**
	 * @param idMessage the idMessage to set
	 */
	public void setIdMessage(Long idMessage) {
		IdMessage = idMessage;
	}

	/**
	 * @return the idSender
	 */
	public int getIdSender() {
		return IdSender;
	}

	/**
	 * @param idSender the idSender to set
	 */
	public void setIdSender(int idSender) {
		IdSender = idSender;
	}
}
