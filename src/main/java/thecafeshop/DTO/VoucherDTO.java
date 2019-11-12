package thecafeshop.DTO;


import thecafeshop.entity.Voucher;

public class VoucherDTO {

	private Voucher voucher;

	private Boolean canDelete;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

}
