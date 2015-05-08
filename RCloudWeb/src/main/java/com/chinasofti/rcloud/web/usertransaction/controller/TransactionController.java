package com.chinasofti.rcloud.web.usertransaction.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.PageParameter;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.TransactionRecordEntity;
import com.chinasofti.rcloud.portal.userconsole.domain.TransactionRecordEntityExt;
import com.chinasofti.rcloud.portal.userconsole.service.TransactionManagerService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.CommonUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;

/**
 * 交易管理Controller
 * 
 * @author wanggq
 *
 */
@Controller
public class TransactionController extends BasicController {

	private Logger logger = Logger.getLogger(TransactionController.class);

	@Autowired
	private TransactionManagerService transactionManagerService;

	/**
	 * 按条件查询交易账单
	 * 
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping("page/transaction/findTsatList")
	public String findTsatList(HttpServletRequest request, Model model) {
		return "transaction/transactionList";
	}

	/**
	 * 
	 * @Title: getEntityById
	 * @Description: 返回校验详情
	 * @author sunlulu
	 * @param @param Id
	 * @param @return
	 * @return ResponseEntity<TransactionRecordEntity>
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@RequestMapping("/rest/transaction/detail/{Id}")
	@ResponseBody
	public ResponseEntity<TransactionRecordEntity> getEntityById(
			@PathVariable("Id") String Id) {
		ResponseEntity<TransactionRecordEntity> responseEntity = new ResponseEntity<TransactionRecordEntity>();
		try {
			TransactionRecordEntity transactionRecordEntity = transactionManagerService
					.getTransactionRecordEntityById(Id);
			responseEntity.setEntity(transactionRecordEntity);
		} catch (RCloudException re) {
			handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 按条件查询交易账单
	 * 
	 * @return
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_USER_ADMIN)
	@ResponseBody
	@RequestMapping("rest/transaction/getTsatList")
	public ResponseEntity<Pagination<TransactionRecordEntityExt>> getTsatList(
			HttpServletRequest request, Model model) {
		ResponseEntity<Pagination<TransactionRecordEntityExt>> responseEntity = new ResponseEntity<Pagination<TransactionRecordEntityExt>>();
		String userId = LoginUtil.getUserId();
		if (StringUtils.isEmpty(userId)) {
			return responseEntity;
		}
		Map<String, Object> searchParams = CommonUtil.getParameterMap(request);

		PageParameter page = new PageParameter();

		if (!StringUtils.isEmpty(searchParams.get("page"))
				&& !StringUtils.isEmpty(searchParams.get("rows"))) {
			page.setCurrentPage(Integer.parseInt((String) searchParams
					.get("page")));
			page.setPageSize(Integer.parseInt((String) searchParams.get("rows")));
		}

		searchParams.put("page", page);
		searchParams.put("userId", userId);

		String transactionType = request.getParameter("transactionType");
		if (transactionType != null && !transactionType.equals("")) {
			int transactionTypess = Integer.parseInt(transactionType);
			searchParams.put("transactionType", transactionTypess);
		}

		String moneyFlow = request.getParameter("moneyFlow");
		if (moneyFlow != null && !moneyFlow.equals("")) {
			int moneyFlowss = Integer.parseInt(moneyFlow);
			searchParams.put("moneyFlow", moneyFlowss);
		}

		try {
			String startT = request.getParameter("beginTime");
			String endT = request.getParameter("endTime");
			SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
			if (!StringUtils.isEmpty(startT)) {
				searchParams.put("beginTime", sdt.parse(startT));
			}
			if (!StringUtils.isEmpty(endT)) {
				searchParams.put("endTime",
						com.chinasofti.rcloud.common.CommonUtil
								.getAfterDay(endT));
				// searchParams.put("endTime", sdt.parse(endT));
			}

			Pagination<TransactionRecordEntityExt> pagination = transactionManagerService
					.findListByPage(searchParams);

			List<TransactionRecordEntityExt> list = pagination.getRows();
			if (list != null && list.size() > 0) {
				int i = 0;
				for (TransactionRecordEntityExt entity : list) {
					SimpleDateFormat dsdt = new SimpleDateFormat("yyyy年MM月dd日");
					entity.setDateShow(dsdt.format(entity.getTransactionTime()));
					list.set(i, entity);
					i++;
				}
			}

			responseEntity.setEntity(pagination);
		} catch (RCloudException e) {
			logger.error("交易检查出错", e);
			this.handleBusinessExceptionByJson(e, responseEntity);
		} catch (Exception e) {
			logger.error("交易检查出错", e);
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;
	}
}
