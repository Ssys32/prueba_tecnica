# AppBlog

<div align="center">
<img style=:"width="400" height="400" src="https://github.com/Ssys32/prueba_tecnica/blob/master/Screenshot_20231222_151506_com.example.appblog_031805.jpg?raw=true"/>
<img style=:"width="400" height="400" src="https://github.com/Ssys32/prueba_tecnica/blob/master/Screenshot_20231222_151607_com.example.appblog_031759.jpg?raw=true"/>
</div> 

## Tabla de contenidos:
---

- [Descripción y contexto](#descripción-y-contexto)
- [Guía de usuario](#guía-de-usuario)
- [Guía de instalación](#guía-de-instalación)
- [Dependencias](#dependencias)
- [Autor/es](#autores)
- [Información adicional](#información-adicional)
- [Licencia](#licencia)
- [Limitación de responsabilidades - Solo BID](#limitación-de-responsabilidades)


## Descripción y contexto
---
Aplicación para Android en Kotlin con un servicio web en PHP que posea un conjunto reducido de características propias de un Blog de Internet.
El servicio web esta alojado en un host gratuito https://es.000webhost.com/.

## Guía de usuario
---
<div align="center">
<img style=:"width="400" height="400" src="https://github.com/Ssys32/prueba_tecnica/blob/master/Screenshot_20231222_151506_com.example.appblog_031805.jpg?raw=true"/>
<img style=:"width="400" height="400" src="https://github.com/Ssys32/prueba_tecnica/blob/master/Screenshot_20231222_151607_com.example.appblog_031759.jpg?raw=true"/>
</div> 
 
Pantalla de inicio se pueden identificar la lista de entradas del blog y un boton flotante que nos indica que nos llevara a la vista para agregar una nueva
entrada del blog.
Esta vista tambien nos desplegara las entradas que se tengan almacenadas de forma local, estas entradas solo se mostraran cuando no se tenga conexion a la red
en caso de no tener ninguna entrada almacenada de forma local se mostrara vacia la lista y el boton de agregar estara desactivado

<div align="center">
<img style=:"width="400" height="400" src="https://github.com/Ssys32/prueba_tecnica/blob/master/Screenshot_20231222_151530_com.example.appblog_031804.jpg?raw=true"/>
<img style=:"width="400" height="400" src="https://github.com/Ssys32/prueba_tecnica/blob/master/Screenshot_20231222_151611_com.example.appblog_031758.jpg?raw=true"/>
</div> 

Esta vista nos muestra un formulario de tres campos, los cuales son necesarios para crear una nueva entrada y un boton de guardar para insertarla en la base de datos
a travez del servicio web

<div align="center">
<img style=:"width="400" height="400" src="https://github.com/Ssys32/prueba_tecnica/blob/master/Screenshot_20231222_151542_com.example.appblog_031802.jpg?raw=true"/>
<img style=:"width="400" height="400" src="https://github.com/Ssys32/prueba_tecnica/blob/master/Screenshot_20231222_151650_com.example.appblog_031754.jpg?raw=true"/>
</div> 

La pantalla anterior muestra los detalles de cada una de las entrada que se encuentra en la lista, podemos acceder a ella al tocar cada elemento de la lista 
de entradas.
Esta pantalla tambien nos mostrara los detalles de las entradas guardadas de forma local

 
## Guía de instalación
---

Las herramientas que se utilizaron en el desarrollo de esta aplicacion son: 


  - Android Studio Bumblebee: version-11.0.11+9-b60-7590822 amd64
  - MySQL: 5.7.21
  - PHP: 7.4
  - Postman: 9.15.6

## Dependencias
Este proyecto hace uso de difentes librerias y permisos del dispositivo que a continuacion se listan

Retrofit

    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    
KTX ViewModel y LiveData

    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.4.1'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    
Navigation Components

    implementation 'androidx.navigation:navigation-fragment-ktx:2.4.1'
    implementation 'androidx.navigation:navigation-ui-ktx:2.4.1'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'

Coroutines

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    
Room

    implementation 'androidx.room:room-ktx:2.4.2'
    implementation 'androidx.room:room-runtime:2.4.2'
    kapt 'androidx.room:room-compiler:2.4.2'
    
Java

    JavaVersion.VERSION_1_8
    
Kotlin

    1.6.10    
    
ViewBinding    
    
    buildFeatures {
        viewBinding  = true
    }
    
AndroidManifest.xml

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET"/>
    
Web Service en PHP

    https://api-prueba-tecnica.000webhostapp.com/post.php
    
Enlace de descarga del APK

    https://drive.google.com/file/d/1XRsmt7F51HKNPJIhsLxIFQ42bQCO9mPx/view?usp=sharing
    
  

## Autor/es
---

Jose Maria Gonzalez
Egresado de Ingeniería en Sistemas Computacionales
Email: gonzalezperezj5@gmail.com
