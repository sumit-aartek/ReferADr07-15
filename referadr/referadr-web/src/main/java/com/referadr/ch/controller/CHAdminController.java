package com.referadr.ch.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.util.Throwables;
import com.referadr.Exception.ReferadrException;
import com.referadr.ch.model.CHAdmin;
import com.referadr.ch.model.CHPSPBean;
import com.referadr.ch.model.RadCodes;
import com.referadr.ch.service.CHAdminService;
import com.referadr.login.model.Login;
import com.referadr.login.service.LoginService;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.CHSPMappingBean;
import com.referadr.practice.model.ChSpMapping;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.model.RedState;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;

@Controller
public class CHAdminController {
	
	@Autowired
	private CHAdminService chAdminservice;
	
	@Autowired
	private LoginService loginService;
	

	@RequestMapping(value = "/addCHAdmin", method = RequestMethod.GET) 
		public String addCHAdmin(@RequestParam("chId") int chId,Model model)
		{
		model.addAttribute("chAdmin",new CHAdmin(chId));
		return "CHAdminRegister";
		}
	
	@RequestMapping(value = "/saveCHAdmin", method = RequestMethod.POST) 
	public ModelAndView saveCHAdmin(@ModelAttribute("chAdmin")CHAdmin chAdmin, BindingResult result)
	{
		String navigatePage="";
		int loginRow=0;
		int insertedRows=0;
		chAdmin.setPassword(ReferadrUtils.sha1(chAdmin.getPassword()));
		try
		{
			Login login=new Login(chAdmin.getEmaiId(),chAdmin.getPassword(),1);
			loginRow=loginService.insertLoginCredentials(login);
		if(loginRow == 1)
		{
			 insertedRows=chAdminservice.addCHAdmin(chAdmin);
			
		}
		if(loginRow>0 && insertedRows > 0)
		{
			navigatePage="Registered";
			String body="Hi " + chAdmin.getFirstName() + ", \n Succesfully Registered.Please access the below link for login(Registered Email-Id is the username). \n www.tinyurl.com/referadr";
			ReferadrUtils.sendMail(chAdmin.getEmaiId(), IConstant.FROM_MAIL, "Practice Admin Registration::Successful",body);
		}
		else
		{
			navigatePage="CHAdminRegister";
		}
		}
		catch(Exception e)
		{
			System.out.println("Failed saving clearing house admin:"+e.getMessage());
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Clearing House Admin Registration", errors.toString());
			throw new ReferadrException("Failed saving clearing house admin.Please contact administrator");
		}
	return new ModelAndView(navigatePage);
	}

	@RequestMapping(value = "/updateCHInfo", method = RequestMethod.POST) 
	public String updateCHInfo(@ModelAttribute("chInfo")CHInfo chInfo,HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		Login login=(Login)session.getAttribute("login");
		try
		{
		int locId=chInfo.getRadLocation().getLocId();
		//locId=999999;
		if(locId==999999)
		{
			long newLocId=chAdminservice.insertLocationDetails(chInfo, login.getUserName());
			RadLocation radLocation = new RadLocation();
			radLocation.setLocId((int)newLocId);
			chInfo.setRadLocation(radLocation);
		}
		else
		{
			int updatedRow=chAdminservice.updateLocationDetails(chInfo, login.getUserName());
		}
		int updatedCHInfo=chAdminservice.updateCHInfo(chInfo, login);
		chInfo=loginService.fetchCHInfo(chInfo.getId());
		List<RedState> states=(List)session.getAttribute("states");
		model.addAttribute("states", states);
		model.addAttribute("chInfo", chInfo);
		}
		catch(Exception e)
		{
			System.out.println("Failed updating clearing house information:updateCHInfo-->"+e.getMessage());
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Updating clearing house information", errors.toString());
			throw new ReferadrException("Failed updating clearing house information.Please contact administrator");
		}
	return "ChHome";
	}
	
	@RequestMapping(value = "/chAdmin", method = RequestMethod.GET) 
	public String displayCHAdminInfo(Model model,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		Login login=(Login)session.getAttribute("login");
		try
		{
		CHAdmin chAdmin=chAdminservice.fetchCHAdmin(login.getUserName());
		model.addAttribute("chAdmin", chAdmin);
		}catch(Exception e)
		{
			System.out.println("Failed fetching chadmin information:"+e.getMessage());
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Fetching Clearing House Admin Information", errors.toString());
			throw new ReferadrException("Failed fetching Clearing House Admin information");
		}
		return "ChAdmin";
	}
	
	@RequestMapping(value = "/updateCHAdmin", method = RequestMethod.POST) 
	public String updateCHAdminInfo(@ModelAttribute("chAdmin")CHAdmin chAdmin,Model model)
	{
		//chAdmin.setPassword(ReferadrUtils.sha1(chAdmin.getPassword()));
		try
		{
		int updateChAdmin=chAdminservice.updateCHAdmin(chAdmin);
		model.addAttribute("chAdmin", chAdmin);
		}catch(Exception e)
		{
			System.out.println("Failed updating chadmin information:"+e.getMessage());
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Updating clearing house Admin Information", errors.toString());
			throw new ReferadrException("Failed updating Clearing House Admin information");
		}
		return "ChAdmin";
	}
	
	@RequestMapping(value = "/viewCHAdmins", method = RequestMethod.GET) 
	public String viewCHAdminsInfo(Model model,HttpServletRequest request,@ModelAttribute("command")CHAdmin chAdmin)
	{
		HttpSession session=request.getSession();
		Login login=(Login)session.getAttribute("login");
		//Map<String,Object> model=new HashMap<String,Object>();
	//	System.out.println("login.UserName--->"+login.getUserName());
		try{
			if(login!=null){
		CHAdmin chAdminObject=chAdminservice.fetchCHAdmin(login.getUserName());
		List<CHAdmin> chAdmins=chAdminservice.fetchCHAdmins(chAdminObject.getChId());
		List<PracticeSpecialty> practiceSpecialties=chAdminservice.fetchPracticeSpecialtyInfo();
		model.addAttribute("practiceSpecialties", practiceSpecialties);
		model.addAttribute("chAdmins", chAdmins);
			}else{
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do"; 
			}
		}
		catch(Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Displaying clearing house Admins information", errors.toString());
			throw new ReferadrException("Failed displaying CH Adminastrators Informaton");
		}
		return "CHAdmins";
	}
	
	@RequestMapping(value = "/viewPractices", method = RequestMethod.GET) 
	public String viewPracticeInfo(Model model,HttpServletRequest request,@ModelAttribute("command")CHPSPBean chpsp)
	{
		 HttpSession session = request.getSession();
			Login login = (Login) session.getAttribute("login");
		
		try
		{
		if(login!=null){
		List<String> practiceSpDesc=new ArrayList<String>();
		//HttpSession session=request.getSession();
		int chId=(Integer)session.getAttribute("clearingHouseId");
//		List<ChSpMapping> practiceSpecialties=chAdminservice.fetchCHPracticeSpecialtyMapping(chId);//chAdminservice.fetchPracticeSpecialtyInfo();
//		//int chId=Integer.parseInt(id);
//		for(int i=0;i<practiceSpecialties.size();i++)
//		{
//			practiceSpDesc.add(practiceSpecialties.get(i).getPraticeSplDesc());
//		}
		List<ChSpMapping> chSpMappingList=chAdminservice.fetchCHPracticeSpecialtyMapping(chId);
		List<PracticeSpecialty> practiceSpeciality=chAdminservice.fetchPracticeSpecialtyInfo();
		for(int i=0;i<chSpMappingList.size();i++)
		{
			for(int j=0;j<practiceSpeciality.size();j++)
			{
				//System.out.println("practiceSpeciality.get(j).getPraticeSplID()-->"+practiceSpeciality.get(j).getPraticeSplID());
				//System.out.println("chSpMappingList.get(i).getPratice_Spl_ID()--->"+chSpMappingList.get(i).getPratice_Spl_ID());
				if(practiceSpeciality.get(j).getPraticeSplID()==chSpMappingList.get(i).getPratice_Spl_ID())
				{
					practiceSpDesc.add(practiceSpeciality.get(j).getPraticeSplDesc());
					break;
				}
			}
			
		}
		List<RadCodes> fetchCategories=chAdminservice.fetchCodes("PRAC_CAT");
		List<CHPSPBean> chPracticesInfo=chAdminservice.fetchPracticesInfo(chId);
		model.addAttribute("categories",fetchCategories);
		model.addAttribute("practiceSpecialties", practiceSpDesc);
		model.addAttribute("chpsp", new CHPSPBean());
		model.addAttribute("chPracticesInfo",chPracticesInfo);
		}else{
			model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do"; 
		}
		}catch(Exception e)
		{
			System.out.println("Failed displaying practices information:viewPracticeInfo-->"+e.getMessage());
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Displaying the practices information", errors.toString());
			throw new ReferadrException("Failed displaying practices information.Please contact administrator");
		}
		/*return "CHPractice";*/
		return "CHPracticeNew";
		
	}
	
	@RequestMapping(value = "/addPractice", method = RequestMethod.POST) 
	public String addPracticeInfo(Model model,HttpServletRequest request,@ModelAttribute("command")CHPSPBean chpsp)
	{
		List<Integer> pracSpecId=new ArrayList<Integer>();
		HttpSession session=request.getSession();
		int chId=(Integer)session.getAttribute("clearingHouseId");
		try{
		for(int i=0;i<chpsp.getSp().size();i++)
		{
			pracSpecId.add(chAdminservice.fetchPracSpecId(chpsp.getSp().get(i)));
		}
		chpsp.setSpId(pracSpecId);
		long newPracticeId=chAdminservice.addPracticeInfo(chpsp);
		if(newPracticeId > 0)
		{
			int insertedCnt=chAdminservice.insertPSPMapping(chpsp.getSpId(), newPracticeId);
			int chpracrow=chAdminservice.insertCHPracticeMapping((int)newPracticeId, chId);
		}
		String body="Hi " + chpsp.getName() + ", \n Please access the below link for registration. \n http://default-environment-fv2kbsasei.elasticbeanstalk.com/providerRegistration.do?practiceId="+newPracticeId+"&staffId=477";
		ReferadrUtils.sendMail(chpsp.getEmailId(), chpsp.getEmailId(), "Practice Admin Registration",body);
		}catch(Exception e)
		{
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Adding Practices", errors.toString());
			throw new ReferadrException("Failed adding Practices.Please contact administrator");
		}
				return "redirect:/viewPractices.do";
	}
	
	
	@RequestMapping(value = "/editCHAdmin", method = RequestMethod.GET) 
	public String editCHAdmin(@RequestParam("chAdminId") int chAdminId,HttpServletRequest request,Model model)
	{
		 HttpSession session = request.getSession();
			Login login = (Login) session.getAttribute("login");
		try
		{
			if(login!=null){
		CHAdmin chAdmin=chAdminservice.editCHAdmin(chAdminId);
		model.addAttribute("chAdmin", chAdmin);
			}else{
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do"; 
			}
		}catch(Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Edit clearing house admin", errors.toString());
			throw new ReferadrException("Failed editing Clearing House Administrator.Please contact administrator");
		}
		return "ChAdmin";
	}
	
	
	@RequestMapping(value = "/addNewCHAdmin", method = RequestMethod.POST) 
	public String addNewCHAdmin(@ModelAttribute("command")CHAdmin chAdmin,BindingResult result,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int chId=(Integer)session.getAttribute("clearingHouseId");
		String body="Hi "+chAdmin.getFirstName()+",\n Please register in the below link \n http://default-environment-fv2kbsasei.elasticbeanstalk.com/addCHAdmin.do?chId="+chId;
		try
		{
		ReferadrUtils.sendMail(chAdmin.getEmaiId(), IConstant.TO_MAIL, "Clearing House Administrator Registration", body);
		}catch(Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Adding Clearing House Administrator", errors.toString());
			throw new ReferadrException("Failed adding new clearing house administrators");
		}
		return "redirect:/viewCHAdmins.do";
	}
	
	@RequestMapping(value = "/masterData", method = RequestMethod.GET) 
	public String masterData(HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		Login login = (Login) session.getAttribute("login");
		try {
			if(login!=null){
				int chId=(Integer)session.getAttribute("clearingHouseId");
			List<ChSpMapping> chSpMappingList=chAdminservice.fetchCHPracticeSpecialtyMapping(chId);
//			for(int i=0;i<chSpMappingList.size();i++)
//			{
//			System.out.println("chSpMappingList-->"+chSpMappingList.get(i).getPratice_Spl_ID());	
//			}
			List<PracticeSpecialty> practiceSpeciality=chAdminservice.fetchPracticeSpecialtyInfo();
			List<String> preCheckedVals = new ArrayList<String>();
			for(int i=0;i<chSpMappingList.size();i++)
			{
				for(int j=0;j<practiceSpeciality.size();j++)
				{
					//System.out.println("practiceSpeciality.get(j).getPraticeSplID()-->"+practiceSpeciality.get(j).getPraticeSplID());
					//System.out.println("chSpMappingList.get(i).getPratice_Spl_ID()--->"+chSpMappingList.get(i).getPratice_Spl_ID());
					if(practiceSpeciality.get(j).getPraticeSplID()==chSpMappingList.get(i).getPratice_Spl_ID() && "Y".equals(chSpMappingList.get(i).getChSplStatus()))
					{
						preCheckedVals.add(practiceSpeciality.get(j).getPraticeSplDesc());
						break;
					}
				}
				
			}
			CHSPMappingBean cs=new CHSPMappingBean();
			cs.setSp(preCheckedVals);
			cs.setChId(1);
			model.addAttribute("cs", cs);
			List<String> specialities=new ArrayList<String>();
			for(int i=0;i<practiceSpeciality.size();i++)
			{
			specialities.add(practiceSpeciality.get(i).getPraticeSplDesc());
			}
			model.addAttribute("specialities",specialities);
		}else{
			model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do"; 
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	return "masterData";
	}
	
	@RequestMapping(value = "/saveCHPSmapping", method = RequestMethod.POST) 
	public String saveCHPSmapping(@ModelAttribute("cs")CHSPMappingBean psInfo, BindingResult result,HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		int chId=(Integer)session.getAttribute("clearingHouseId");
		Login login=(Login)session.getAttribute("login");
		List<Integer> lsNew=new ArrayList<Integer>();
		String service=psInfo.getService();
		String serviceName=psInfo.getServiceName();
		try {
			if(login!=null){
			List<ChSpMapping> chSpMappingList=chAdminservice.fetchCHPracticeSpecialtyMapping(chId);
			List<PracticeSpecialty> practiceSpeciality=chAdminservice.fetchPracticeSpecialtyInfo();
			List<Integer> checkedIds=new ArrayList<Integer>();
			List<Integer> updateCheckedIds=new ArrayList<Integer>();
			List<Integer> unCheckedIds=new ArrayList<Integer>();
			List<Integer> unCheckedDeleteIds=new ArrayList<Integer>();
			for(int i=0;i<practiceSpeciality.size();i++)
			{
				for(int j=0;j<psInfo.getSp().size();j++)
				{
					if(practiceSpeciality.get(i).getPraticeSplDesc().equals(psInfo.getSp().get(j)))
					{
						checkedIds.add(practiceSpeciality.get(i).getPraticeSplID());
						break;
					}
				}
			}
			for(int i=0;i<chSpMappingList.size();i++)
			{
				//System.out.println("chSpMappingList.get(i).getPratice_Spl_ID()--->"+chSpMappingList.get(i).getPratice_Spl_ID());
				if(chSpMappingList.get(i).getChSplStatus().equals("Y"))
				{
				unCheckedIds.add(chSpMappingList.get(i).getPratice_Spl_ID());
				}
			}
			//for(int i=0;i<unCheckedIds.size();i++)
			//{
				for(int j=0;j<unCheckedIds.size();j++)
				{
					if(!checkedIds.contains(unCheckedIds.get(j)))
					{
						unCheckedDeleteIds.add(unCheckedIds.get(j));
					}
				}
			//}
			for(int i=0;i<chSpMappingList.size();i++)
			{
				for(int j=0;j<checkedIds.size();j++)
				{
					if(chSpMappingList.get(i).getPratice_Spl_ID()==checkedIds.get(j))
					{
						if(chSpMappingList.get(i).getChSplStatus().equals("Y"))
						{
						checkedIds.remove(j);
						}
						else if(chSpMappingList.get(i).getChSplStatus().equals("N"))
						{
							checkedIds.remove(j);
							updateCheckedIds.add(chSpMappingList.get(i).getPratice_Spl_ID());
						}
					}
				}
			}
			if(unCheckedDeleteIds.size()>0)
			{
				//will update status flag to N
				int count=chAdminservice.updateCHSpMappingFlag(unCheckedDeleteIds, chId,"N");
			}
			
			if(checkedIds.size()>0)
			{
				int count =chAdminservice.insertCHSpMapping(checkedIds, chId, login.getUserName());
			}
			
			if(updateCheckedIds.size()>0)
			{
				int count =chAdminservice.updateCHSpMappingFlag(updateCheckedIds, chId, "Y");
			}
			System.out.println("serviceName.length()-->"+serviceName.length());
			if(serviceName!=null && !"".equals(serviceName) && ("Specialities").equals(service) && serviceName.length()>0)
			{
				
				int count=chAdminservice.insertNewSpeciality(serviceName, login.getUserName());
			}
			}else{
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do"; 
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/masterData.do";
	}


	
}
