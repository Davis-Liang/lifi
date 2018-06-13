package com.nike.lifi.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.nike.lifi.constants.ConfigEntityConstants;
import com.nike.lifi.constants.LIFIConstants;
import com.nike.lifi.dao.BaseConfigDao;
import com.nike.lifi.dao.UserBatchDao;
import com.nike.lifi.entity.PPKBean;
import com.nike.lifi.exception.ProcessorException;
import com.nike.lifi.util.MessageSourceHelper;
import com.nike.lifi.util.SessionUserHelper;

@Component("ppkProcessor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PPKProcessor extends ConfigAbstractProcessor {

	private List<PPKBean> list = null;

	@Resource(name = "ppkDao")
	private BaseConfigDao<PPKBean> ppkDao;

	@Resource
	private UserBatchDao userBatchDao;

	@Override
	public boolean beforeProcess() {
		// TODO Fake data , need to be impl by Excel reader.
		retrieveData();
		return validateData();
	}

	@Override
	public boolean process() throws ProcessorException {
		int batchId = 0;
		if (CollectionUtils.isNotEmpty(list)) {
			Integer userId = SessionUserHelper.getCurrentUserId();
			ppkDao.deleteByUserIdAndEntityId(userId, ConfigEntityConstants.ENTITY_SHEET_PPK);
			batchId = ppkDao.insert(list);
			if (batchId > 0) {
				userBatchDao.delete(userId, ConfigEntityConstants.ENTITY_SHEET_PPK);
				return userBatchDao.insert(userId, batchId, ConfigEntityConstants.ENTITY_SHEET_PPK);
			}
		}
		return batchId > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean afterProcess() {
		Map<String, Object> ppkMap = (Map<String, Object>) getSharedObject().get(LIFIConstants.CONFIG_SHEET_PPK);
		ppkMap.put(LIFIConstants.CONFIG_SHEET_DATA, ppkDao.list(SessionUserHelper.getCurrentUserId()));
		ppkMap.put(LIFIConstants.CONFIG_SHEET_VALIDATION_MESSAGE, getValidationMessages());
		return true;
	}

	private void retrieveData() {
		PPKBean ppkBean = new PPKBean();
		ppkBean.setPpk("1PPK 6");
		ppkBean.setProdCd("SX4705-101");
		list = new ArrayList<PPKBean>();
		list.add(ppkBean);
	}

	private boolean validateData() {
		boolean flag = true;
		try {
			for (PPKBean bean : list) {
				if (StringUtils.isEmpty(bean.getPpk())) {
					getValidationMessages().add(MessageSourceHelper.getMessage("lifi.config.validation.empty", new String[] { "PPK", "PPK" },
							LocaleContextHolder.getLocale()));
					break;
				}
				Matcher matcher = Pattern.compile("^PPK([-_]|\\s?)(\\d+)", Pattern.CASE_INSENSITIVE).matcher(bean.getPpk());
				if (matcher.find()) {
					bean.setPpkValue(Integer.parseInt(matcher.group(2)));
				} else {
					flag = false;
					throw new NumberFormatException();
				}
			}
		} catch (NumberFormatException e) {
			getValidationMessages().add(MessageSourceHelper.getMessage("lifi.config.validation.format.wrong", new String[] { "PPK", "PPK" },
					LocaleContextHolder.getLocale()));
			flag = false;
		}
		return flag;
	}

}
