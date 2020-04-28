package utils;

import daos.daosActividad.JPADaoActividad;
import daos.daosUser.IDaoUser;
import daos.daosUser.JPADaoUser;
import daos.daosUsuario.IDaoUsuario;
import daos.daosUsuario.JPADaoUsuario;
import daos.daosActividad.IDaoActividad;
import daos.DaoAdministrador;
import daos.DaoDificultad;
import daos.DaoFormato;
import daos.DaoFoto;
import daos.DaoPrivacidad;
import daos.DaoPunto;
import daos.DaoRuta;
import daos.DaoValoracion;

public abstract  class Factory {
	
      public static IDaoActividad daoActividad(){
    	  return new JPADaoActividad();
      }
      
      public static DaoAdministrador daoAdministrador(){
    	  return new DaoAdministrador();
      }
      public static DaoFormato daoFormato(){
    	  return new DaoFormato();
      }
      public static DaoDificultad daoDificultad(){
    	  return new DaoDificultad();
      }
      public static DaoPrivacidad  daoPrivacidad(){
    	  return new DaoPrivacidad();
      }
      public static DaoFoto  daoFoto(){
    	  return new DaoFoto();
      }
      public static DaoPunto  daoPunto(){
    	  return new DaoPunto();
      }
      public static DaoRuta  daoRuta(){
    	  return new DaoRuta();
      }
      public static IDaoUser  daoUser(){
    	  return new JPADaoUser();
      }
      public static IDaoUsuario  daoUsuario(){
    	  return new JPADaoUsuario();
      }
      public static DaoValoracion  daoValoracion(){
    	  return new DaoValoracion();
      }
      
}

