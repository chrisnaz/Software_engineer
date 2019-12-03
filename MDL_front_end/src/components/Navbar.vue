<template>
      
  <nav app> 
    <v-toolbar app height="94px" flat class="grey_custom">
        <a href="/"> 
      <img src="/header_logo.png" height="62px" width="270px" alt="logo">  
       </a>
      
      <v-spacer></v-spacer>

    
  
    <!-- si log -->
    <div v-if="accessToken"> 
      <v-layout mr-2 row>
        <v-flex mr-2>
          <!-- Menu -->
        <v-menu flat full-width open-on-hover bottom left>
          <v-btn flat slot="activator" class="grey_custom white--text" block>
            <v-layout column justify-center>
              <v-flex>
                  <h5> {{name}} ({{status}}) </h5>
              </v-flex>
              <v-flex>
                  <v-icon> expand_more </v-icon>
                  </v-flex>
            </v-layout>
                  
          </v-btn>
          <!-- Liste -->
          <v-list class="grey_custom white--text">
              <v-list-tile  active-class="white--text" v-for="item in items" :key="item.title" router :to="item.route" > 
                <v-list-tile-title> 
                  
                  <v-icon class="white--text" left> {{item.icon}} </v-icon> 
                  <span class="white--text"> {{item.title}} </span>      
                </v-list-tile-title>
              </v-list-tile>
              <v-list-tile active-class="white--text" to="/" @click="logoutSubmit">
              <v-list-tile-title>
                <v-icon class="white--text" left> power_settings_new </v-icon> 
                  <span class="white--text"> Log out </span>      
                </v-list-tile-title>
              </v-list-tile>
          </v-list>
      </v-menu>
        </v-flex>
        <!-- Avatar -->
        <v-flex>
        <v-avatar>
          <img
            :src="avatar">
        </v-avatar>
        </v-flex>
      </v-layout>

    </div>
    <!-- sinon -->
    <div v-else>
      <v-btn flat round to="/signup">
        <span class="white--text" text-transform: lowercase> Sign up </span>
        
    </v-btn> 

    <v-btn flat round class="white" to="/login" >
        <span class="gey darken-1--text"> Login </span>
    </v-btn>
      
    </div>

    </v-toolbar>
  </nav>
</template>

<script>
import axios from 'axios'
import { mapState, mapActions } from 'vuex';
import { access } from 'fs';
export default {
  data () {
    return {
        avatar :'http://www.allodessin.com/tuts/51/dessin-de-cochon-mignon-de-dessin-anime.jpg',
        name :'Petit Lard',
        status:'Researcher',
        items :[
          {title: 'My Profile', icon: 'perm_identity', route:'/profile'},
          {title: 'My State of the Art', icon: 'list_alt', route:'/mySoa'}
          //{title: 'Favorite', icon: 'favorite', route:'/favorite'},
         
        ]
    }
  },
   methods: {
     ...mapActions([
        'doLogout'
      ]),
     logoutSubmit(){
       
        this.doLogout({
          

        })
     }
   },
   created(){

      axios({

        method: 'GET',
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/UsersName/'+localStorage.getItem('accessUsername').toString()
      }).then(response => {
          this.name = response.data.lastname + " " +response.data.firstname;;
          this.status =response.data.statut;
      })

   },
   computed: {
      ...mapState([
        'loggingIn',
        'loginError',
        'accessToken'
      ])
    }

   
  }
</script>
