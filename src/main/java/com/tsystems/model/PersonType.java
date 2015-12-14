package com.tsystems.model;
/**
 * There are 3 types of actors:
 * 1)client can make Orders.
 * 2)sales manager manages Product's set and their Properties.
 * 3)administrator has manager's privileges and can create new sales managers.
 * @author float
 *
 */
public enum PersonType {
	ROLE_CLIENT, ROLE_SALES_MANAGER, ROLE_ADMIN,NONE
}
