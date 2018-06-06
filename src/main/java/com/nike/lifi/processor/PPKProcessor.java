package com.nike.lifi.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nike.lifi.dao.BaseConfigDao;
import com.nike.lifi.entity.PPKBean;
import com.nike.lifi.exception.ProcessorException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PPKProcessor implements BaseProcessor {

	private List<PPKBean> list = null;

	@Resource(name="ppkDao")
	private BaseConfigDao<PPKBean> ppkDao;

	@Override
	public boolean beforeProcess() {
		// TODO Fake data , need to be impl by Excel reader.
		retrieveData();
		return validateData();
	}

	@Override
	public boolean process() throws ProcessorException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean afterProcess() {
		// TODO Auto-generated method stub
		return true;
	}

	private void retrieveData() {
		PPKBean ppkBean = new PPKBean();
		ppkBean.setPpk("PPK 6");
		ppkBean.setProdCd("SX4705-101");
		list = new ArrayList<PPKBean>();
		list.add(ppkBean);
	}

	private boolean validateData() {
		boolean flag = true;
		try {
			for (PPKBean bean : list) {
				Matcher matcher = Pattern.compile("PPK([-_]|\\s?)(\\d+)", Pattern.CASE_INSENSITIVE)
						.matcher(bean.getPpk());
				if (matcher.find()) {
					bean.setPpkValue(Integer.parseInt(matcher.group(2)));
				} else {
					flag = false;
					break;
				}
			}
		} catch (NumberFormatException e) {
			flag = false;
		}
		return flag;
	}
}
