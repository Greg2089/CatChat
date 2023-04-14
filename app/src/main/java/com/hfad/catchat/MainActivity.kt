package com.hfad.catchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.MaterialToolbar
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*Чтобы панель инструментов вела себя как обычная панель приложений,
        вам нужно указать MainActivity рассматривать ее  как таковую.
        Вы делаете это, вызывая метод setSupportActionBar() в коде Kotlin activity и
        передавая ему ссылку на панель инструментов следующим образом:*/
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //region Настройка toolbar, чтобы она включала кнопку Up, и отображала, к какому экрану перешли
        /**получение ссылки на navigation controller из navigation host*/
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //получаем ссылку на drawer
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        /**Создаем конфигурацию, которая связывает toolbar c navigation graph*/
        val builder = AppBarConfiguration.Builder(navController.graph)
        //Добавляем drawer на AppBarConfiguration
        builder.setOpenableLayout(drawer)
        val appBarConfiguration = builder.build()
        /** Строка отвечает за конфигурацию на toolbar*/
        toolbar.setupWithNavController(navController, appBarConfiguration)

        //endregion

        //region bottom navigation bar
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavView.setupWithNavController(navController)
        //endregion

        //region Drawer
        //Включаем навигацию при нажатии элемента, связав drawer с navigation controller
        val navView = findViewById<NavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(navView, navController)
        //endregion
    }

    /** Эти регионы относятся к toolbar*/
    //region Добавляем пункты меню в toolbar (в данном случае Help ?)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //endregion

    //region Переход к месту назначения при нажатие элемента
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) ||
                return super.onOptionsItemSelected(item)

    }
    // endregion
}