package thecafeshop.DAOImp;

import thecafeshop.entity.Voucher;

import java.util.List;


public interface VoucherDAOImp extends CommonDAOImp {

	public Boolean addVoucher(Voucher voucher);

	public List<Voucher> findAll();

	public List<Voucher> findLimit(int startPosition);

	public Voucher findById(int voucherid);

	public Voucher findByName(String name);

	/* check voucher date start<now<= date finish, voucher number > 0 */
	public Boolean checkVoucher(String name);

	public Boolean deleteVoucher(int voucherid);

	public Boolean editVoucher(Voucher voucher);

}
