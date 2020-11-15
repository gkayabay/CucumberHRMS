package com.hrms.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CloudLoginPageElements {

	@FindBy(xpath = "//input[@name='username']")
	public WebElement Username;
	
	@FindBy(xpath = "//input[@name='password']")
	public WebElement Password;
	
	@FindBy(css = "button[type='submit']")
	public WebElement Submit;
	
	//Dashboard Elements
	@FindBy(xpath = "//div[@data-test='greetings-nav-right']/span/div")
	public WebElement HelloText;
	
	@FindBy(xpath = "//button[@data-test='sign-out-button']")
    public WebElement SignOut;
	
	@FindBy(name = "postTitle")
	public WebElement Title;
	
	@FindBy(xpath = "//textarea[@name='postBody']")
	public WebElement TextAreaBlog;

	@FindBy(xpath = "//textarea[@name='postBody']/following-sibling::input")
	public WebElement BlogSubmit;
	
	@FindBy(xpath = "//p[@class='like-button'][1]")
	public WebElement LikeOne;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
