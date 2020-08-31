package com.antel.recyclerviewcodelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private var listHeroes: ArrayList<Hero> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        listHeroes.addAll(HeroesData.listData)
        showHeroesList()
    }

    private fun showHeroesList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(listHeroes)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback{
                override fun onItemClicked(item: Hero) {
                    showSelectedHero(item)
                }
            })
    }

    private fun showHeroesGrid() {
        rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(listHeroes)
        rvHeroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallback{
                override fun onItemClicked(item: Hero) {
                    showSelectedHero(item)
                }
            })
    }

    private fun showHeroesCard() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        rvHeroes.adapter = CardHeroAdapter(listHeroes)
    }

    private fun showSelectedHero(hero: Hero){
        Toast.makeText(this, "You Choose ${hero.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        handleMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarTitle(title: String){
        supportActionBar?.title = title
    }

    private fun handleMenu(selectedMenu: Int){
        when(selectedMenu){
            R.id.action_list -> {
                showHeroesList()
                setActionBarTitle("Mode List")
            }

            R.id.action_grid -> {
                showHeroesGrid()
                setActionBarTitle("Mode Grid")
            }

            R.id.action_cardview -> {
                showHeroesCard()
                setActionBarTitle("Mode CardView")
            }
        }
    }
}