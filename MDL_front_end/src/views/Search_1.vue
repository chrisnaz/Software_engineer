<template>
  <div class="search">

    <!-- ------------------- Barre de recherche -------------------- -->
  
      <v-layout column >
        <v-flex xs12 >
          <v-layout align-center justify-start row wrap>
              <v-layout row wrap>
                <v-flex xs11 >
                    <v-text-field class=" px-3" 
                                flat
                                color="pink_custom" 
                                single-line 
                                label="Search ..."  
                                solo
                                v-model="searched_words"
                                @keyup.enter="show_visu"
                                style="margin-left: 130px; border: 1px solid grey ; border-radius: 40px ; height: 64px; padding: 3px 20px;" >
                    </v-text-field>
                    <span style="margin-left: 140px; " class="grey_message--text"> If field remain empty, all state of the art will be shown </span>

                </v-flex>
                <v-flex xs1>
                    <v-btn @click="show_visu" right fab flat class="ml-4 pink_custom" color="white"> 
                        <v-icon> search </v-icon>
                    </v-btn>
                </v-flex>
                </v-layout>            
            <!-- More criterias -->
            <v-flex xs1>
              <v-layout align-center justify-center>
                <span left top flat color="grey_custom" class="subheading" style="margin: 0px 20px; text-decoration: underline">
                  Search by :
                </span>
              </v-layout>
            </v-flex>

            <v-flex xs6>
              <v-layout align-center justify-start>
                <span> Date Range : </span>
                <v-range-slider
                    left
                    color="pink_custom"
                    v-model="dateRange"
                    @change="allModify()"
                    :max="new Date().toISOString().substr(0, 4)"
                    :min="1900"
                    :step="1"
                    style="width:20px; margin: 50px; padding-top: 20px; margin-bottom: 20px;"
                    thumb-label="always">
                </v-range-slider>
              
                <v-text-field
                box
                height=22
                style="margin-right: 130px;"
                v-model="title"
                @change="allModify()" 
                label="Title"
                flat
                color="pink_custom"
                background-color="light_grey_background">
                </v-text-field>
              
              </v-layout>
            </v-flex>


          </v-layout>
        </v-flex>
      </v-layout>
    
    <!-- ------------------- Barre de recherche et more criteria -------------------- -->

    <!-- Container for cluster visualization -->
    <v-card class="rounded" :aspect-ratio="16/9" 
        tile
        color="grey_custom"
        style="margin: 100px; margin-top: 0px"
    >
       
        <v-toolbar 
          class="grey_custom"
          flat>

          <!-- Legend -->
          
          <v-container>
              <v-layout row wrap>
                <v-card-title> <span class="white--text title font-weight-bold"> Legend : </span> </v-card-title>
                  <v-flex v-for="[color, name] in clusterNames" :key="[color, name]" xs2>
                      <v-card flat color="transparent">
                          <v-card-text class="px-0"><v-avatar :color=color size=15></v-avatar> <span class="white--text"> {{name}} </span></v-card-text>
                      </v-card>
                  </v-flex>
              </v-layout>
          </v-container>  
          <v-spacer></v-spacer>

          <!-- Save visualization button -->
          <div v-if="bool_saved">
            <v-btn v-if="accessToken" width="54px" height="54px" depressed class="grey_custom" @click="dialog = true" 
            :disabled="this.saved">
            <span class="white--text" >Save visualization</span>
            <img class="rightspace" src="../assets/save_button.png" width="34px" height="34px" alt="save_logo">
          </v-btn>
          </div>
        </v-toolbar>
        <v-divider style="margin:5px" class="ligt_grey_custom"></v-divider>
        <!--If no result-->
      <div v-if="this.some_data.nodes[0].id =='empty'" class="white--text title text-xs-center" > No result found</div>
        <!-- Visualisation integration -->
        <div id="alchemy" style="zoom:0.1%" class="alchemy" ></div>

        
    </v-card>

  <!-- Pop up save visualization -->
    <div class="text-xs-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <v-card>
        <v-card-title
          class="pink_custom--text title font-weight-bold"
        >
          Save Visualization
        </v-card-title>

        <v-card-text>
            <v-flex>
                <p class="text-xs-center"> Are you sure you want to save this visualization? </p>

            </v-flex>
        </v-card-text>

        <v-divider class="footer_grey save-divider"></v-divider>

        <v-card-actions>
            <v-btn round outline class="cancel-btn ligt_grey_custom" flat @click="dialog = false">Cancel</v-btn>
          <v-spacer></v-spacer>
          
          <v-btn
            round 
            depressed 
            color="pink_custom white--text"
            style="margin-right: 30px"
            @click="save_visu()"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>



<!-- SOA popup -->
<div class="text-xs-center">
    <v-dialog
      v-model="dialog2"
      width="450"
      :data="current_val"
    >

      <v-card>
        <v-card-actions>
            <v-card-title
            class="subheading grey_custom--text"
            primary-title
            style="padding:5px"
            >
            
            {{some_data.nodes[current_val].title}}

            </v-card-title>

            <v-btn icon class="white pink_custom--text"
            style="margin-bottom: 25px; margin-left: auto;"
            @click="()=>{dialog2 = false}">
                <v-icon>close</v-icon>
            </v-btn>
        </v-card-actions>

        <div style="padding-bottom: 15px">
            <div style = "padding-left: 15px; max-width: 50%; display: inline;">

            <a :href="'/modHistory?id='+some_data.nodes[current_val].id+'&tags='+redirect_tags" class="pink_custom--text">{{some_data.nodes[current_val].date}}</a>

            </div>
            <div style = "padding-left: 20%; max-width: 50%; display: inline">
                <a  :href="'/profile?id='+some_data.nodes[current_val].user.id+'&tags='+redirect_tags" class="pink_custom--text">{{some_data.nodes[current_val].user.lastname + " " + this.some_data.nodes[current_val].user.firstname + ' (' + this.some_data.nodes[current_val].user.establishment + ')'}} </a>
            </div><br>

            <div style = "padding-left: 15px; padding-right: auto; display: inline;">
              <span class="grey_custom--text"> less than {{some_data.nodes[current_val].role}} articles </span>
            </div>
        </div>


        <div v-for="item in some_data.nodes[current_val].tags" :key="item.name" style = "padding: 0; padding-left: 15px;" class="body-1">
          <table style="width:50%; float: left; margin-right: 50%" class="text-xs-left">
            <tr>
              <td class="text-xs-left">{{item.name}}</td>
              <td class="pink_custom--text text-xs-right">{{(item.value) * 100}}%</td>
            </tr>
          </table>
          <!--<div class="pink_custom" style="width:40%; margin: 5px; margin-left: auto; margin-right: 15px;">{{current_val}}</div>-->


        </div>

        <!--<div class='hello white' style="width:50%">
          <v-chart class="white--text white" style="padding: 0" v-bind:chartData="chartData"></v-chart>
        </div>-->


        <v-card-actions>
          <v-btn
            style="border-radius:10px" 
            block
            depressed 
            color="light_pink_button white--text"
            :href="'/soa?id=' + this.some_data.nodes[current_val].id +'&tags=' +this.redirect_tags + '&user=' + this.some_data.nodes[current_val].user.id"
          >
            View
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
  </div>
</template>
 
<script src="../../public/Alchemy-master/dist/alchemy.min.js"></script>
<script src="../../public/Alchemy-master/dist/scripts/vendor.js"></script>

<script>
import axios from 'axios'
 import { mapState, mapActions } from 'vuex';
 import router from '../router';
import { version } from 'punycode';
import Vue from 'vue';
//Vue.forceUpdate();


let urlParams = new URLSearchParams(window.location.search);
let url_tags = urlParams.get('tags')

//var clusters =[["#1B9E77","un"], ["#D95F02","deux"], ["#7570B3","trois"], ["#E7298A","quatre"], ["#66A61E","cinq"], ["#E6AB02","six"]];

var colorsClusters = ["#1B9E77", "#D95F02", "#7570B3","#E7298A","#E6AB02","#c90a0a","#0e3eaf","#60f2a4"];
var namesClusters = ["rien", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix", "onze", "douze"]


var tmptags = [{
                
              }];
var data_maggle = {
        "nodes": [
          {
            "id": "empty",
            "user": {
              "firstname": "none",
              "lastname": "none"
            }
          }
          
          
        ]
        
      };
      
  //new Date().getFullYear() 
export default {
  data: () => ({
        id_user:null,
        modify_blogs_title:[],
        modify_blogs_date:[],
        modify_blogs_all:[],
        redirect_tags: '',
        dialog: false,
        dialog2: false,
        current_val: 0,
        bool_saved: true,
        name_visu:"",
        saved: false,
        some_data: data_maggle,
        dateRange : [1900,new Date().toISOString().substr(0, 4)],
        title:'',

        //test légende
        //[["#1B9E77","un"], ["#D95F02","deux"], ["#7570B3","trois"], ["#E7298A","quatre"], ["#66A61E","cinq"], ["#E6AB02","six"]]
        clusterNames:[],

        chartData: {
          chartType: "barChart",
          selector: "chart",
          width: 200,
          height: 100,
          overrides: {
            palette: {
              stroke: "#9A2949"
            },
            x: {
            ticks: 10,
          },
          },
          metric: 'value', // for two or more metrics pass as an array ['count', 'pyCount']
          data: tmptags
        }
    }),
    computed:{
    ...mapState([
        'loggingIn',
        'loginError',
        'accessToken'
      ])},
      
     methods: {
         ...mapActions([
        'doLogin',
        'doLogout'
      ]),
      dateModify: function(){
            this.modify_blogs_date = [];
            
            for (const key in this.some_data.nodes) {
                
                if ( this.dateRange[0] <= this.some_data.nodes[key].date.slice(-4) && this.some_data.nodes[key].date.slice(-4) <= this.dateRange[1])  {
                    this.modify_blogs_date.push(this.some_data.nodes[key])
                    
                }
            }
            //console.log(this.modify_blogs);
        },
        titleModify: function(){
            this.modify_blogs_title = [];
               for (const key in this.some_data.nodes) {
                
                if (this.some_data.nodes[key].title.toUpperCase().includes(this.title.toUpperCase()) && this.title !='' )  {
                    this.modify_blogs_title.push(this.some_data.nodes[key])
                    
                }
            }
            
            //console.log(this.modify_blogs);

        },
        allModify: function(){
            this.modify_blogs_all = [];
            this.dateModify();
            this.titleModify();
            console.log(this.modify_blogs_date);
            if (this.title != ''){
            for (const key in this.modify_blogs_date) {
                for (const key2 in this.modify_blogs_title) {
                    if (this.modify_blogs_date[key].id ==this.modify_blogs_title[key2].id ) {
                        this.modify_blogs_all.push(this.modify_blogs_date[key])
                        
                    }
                }
            }
        }else{
            this.modify_blogs_all = this.modify_blogs_date;
        }
        console.log(this.modify_blogs_all);

        this.new_some_data={
        "nodes": this.modify_blogs_all
        
      };
         
      
      
    
        //sauvegarde du contexte pour l'avoir un sous niveau plus bas
    
    //console.log(json);
      var newconfig = {
            cluster: true,
            backgroundColor: "#4C4C4C",
            dataSource: this.new_some_data,
            nodeTypes: { "role": 
                ["10", "20", "30", "40","50","100","200"] 
            },
            nodeStyle: {
              "10": {
                "radius": 20
            },
            "20": {
                "radius": 30
            },
            "30": {
                "radius": 40
            },
            "40": {
                "radius": 50
            },
            "50": {
                "radius": 60
            },
            "100": {
                "radius": 70
            },
            "200": {
                "radius": 80
            }
        }
           };
           
      alchemy = new Alchemy(newconfig);
      var self = this;
      alchemy.begin({dataSource: this.new_some_data,
      backgroundColor: "#4C4C4C",
      nodeCaption: function(node){
      return node.title;
    }, 
        nodeMouseOver: "title",
         nodeTypes: { "role": 
                ["10", "20", "30", "40","50","100","200"] 
            },
           nodeStyle: {
              "10": {
                "radius": 20
            },
            "20": {
                "radius": 30
            },
            "30": {
                "radius": 40
            },
            "40": {
                "radius": 50
            },
            "50": {
                "radius": 60
            },
            "100": {
                "radius": 70
            },
            "200": {
                "radius": 80
            }
        },
        cluster: true,
        
        //clusterColours: [clusters[0][0],clusters[1][0],clusters[2][0],clusters[3][0],clusters[4][0],clusters[5][0]],
        clusterColours: colorsClusters,
        nodeClick: function(node){console.log(node._properties.id_research); self.dialogShow(node._properties.id_research)}
        })
      
        this.$forceUpdate(); 


        },
      
       save_visu(){
       let urlParams = new URLSearchParams(window.location.search);
       
        var url_tags = urlParams.get('tags').split(',');
         var tags_list = "";
         for( var i=0; i < url_tags.length; i++){
           if(i==url_tags.length-1){
              tags_list +=url_tags[i]
           }
           else {
              tags_list += url_tags[i] + " "
           }
           
         }
         console.log(tags_list);
          axios({
        method: 'GET',
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/UsersName/'+localStorage.getItem('accessUsername').toString()
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/UsersName/'+localStorage.getItem('accessUsername').toString()
      }).then(response => {
          console.log(response.data.id)
          this.id_user = response.data.id;
          console.log(this.id_user);
      })
       var self = this;
        setTimeout(function(){
          axios({
              method: 'POST',
              //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/saveResearch/keyword/'+ tags_list +'/user/'+ self.id_user.toString()
              url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/saveResearch/keyword/'+ tags_list +'/user/'+ self.id_user.toString(),
              headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
              }
            }).then(response => {
                
             
      })
         },1000)
    this.dialog = false
    this.saved = true;
     },

       show_visu: function(){
            var all_inputs = [];

            all_inputs =this.searched_words.split(" ");
            console.log(all_inputs);

            router.push({
                path: '/search',
                query: {
                    tags: all_inputs.join(',')
                }
                });

              router.go()
        
        },
      loginSubmit() {
        this.doLogin({
          username: this.username,
          password: this.password
        });
      } ,
      //filter JSON to adapt the visualisation 
       filter(){
         //do the same as the created() below
         this.some_data={
        "nodes": [
          {
            "id": 1,
            "id_research":1,
            "title": "L’apprentissage de la programmation chez les 10-12 ans avec des micro:bits.",
            "date": "01-12-2017",
            "theabstract": "long text",
            "tags": [
              {
                "name": "Machine Learning",
                "value": 45
              },
              {
                "name": "SVM",
                "value": 22
              },
              {
                "name": "Programmation",
                "value": 10
              },
              {
                "name": "Random Forest",
                "value": 5
              },
              {
                "name": "Python",
                "value": 1
              }
            ],
            "user": {
              "id": 1,
              "username": "user",
              "password": "{bcrypt}$2a$10$x7hjepnY6hD0P1nYMGWKt.6lOGIOva2bNIcUFxaLSwaiKltAqpcbS",
              "passwordConfirm": null,
              "firstname": "Ted",
              "lastname": "Smith",
              "establishment": "MIT",
              "email": "ted.smith@gmail.com",
              "statut": null,
              "roles": [
                "ROLE_USER"
              ],
              "enabled": true,
              "accountNonExpired": true,
              "accountNonLocked": true,
              "credentialsNonExpired": true,
              "authorities": [
                {
                  "authority": "ROLE_USER"
                }
              ]
            },
            "coauthor": null,
            "cluster": 2,
            "role": "10"
          }
          
          
        ]
        
      };
         
      
      
    
        //sauvegarde du contexte pour l'avoir un sous niveau plus bas
    
    //console.log(json);
      var newconfig = {
            cluster: true,
            backgroundColor: "#4C4C4C",
            dataSource: this.some_data,
            nodeTypes: { "role": 
                ["10", "20", "30", "40","50","100","200"] 
            },
            nodeStyle: {
              "10": {
                "radius": 20
            },
            "20": {
                "radius": 30
            },
            "30": {
                "radius": 40
            },
            "40": {
                "radius": 50
            },
            "50": {
                "radius": 60
            },
            "100": {
                "radius": 70
            },
            "200": {
                "radius": 80
            }
        }
           };
           
      alchemy = new Alchemy(newconfig);
      var self = this;
      alchemy.begin({dataSource: this.some_data,
      backgroundColor: "#4C4C4C",
      nodeCaption: function(node){
      return node.title;
    }, 
        nodeMouseOver: "title",
         nodeTypes: { "role": 
                ["10", "20", "30", "40","50","100","200"] 
            },
           nodeStyle: {
              "10": {
                "radius": 20
            },
            "20": {
                "radius": 30
            },
            "30": {
                "radius": 40
            },
            "40": {
                "radius": 50
            },
            "50": {
                "radius": 60
            },
            "100": {
                "radius": 70
            },
            "200": {
                "radius": 80
            }
        },
        cluster: true,
        
        //clusterColours: [clusters[0][0],clusters[1][0],clusters[2][0],clusters[3][0],clusters[4][0],clusters[5][0]],
        clusterColours: colorsClusters,
        nodeClick: function(node){console.log(node._properties.id_research); self.dialogShow(node._properties.id_research)}
        })
      
        this.$forceUpdate(); 
    
          
       },
       dialogShow(info) {
         
         this.current_val = info-1
         this.dialog2 = true;
         this.chartData.data = this.some_data.nodes[this.current_val].tags;
         
      }
      
    },
    created() {
      let urlParams = new URLSearchParams(window.location.search);
        let url_tags = urlParams.get('tags');
        this.redirect_tags = url_tags;
        
       
    },
  mounted: function () {
     let urlParams = new URLSearchParams(window.location.search);
        let url_tags = urlParams.get('tags').split(',');
        //check si la recherche de l'utilisateur est vide
      if(!(url_tags[0] =='' && url_tags.length==1)){
        console.log("not empty");
      
      
        var val = 1;
        var tags_str = "";
        for (const key in url_tags) {
          if (key == url_tags.length-1){
             tags_str += url_tags[key]
          }else{
            tags_str += url_tags[key] + " "
          }
          
        
        }
          axios({
        method: 'GET',
        // http://localhost:8080/research/+tags_str
        //http://mdl-std05.info.fundp.ac.be:8181/mdl_web/research/' + tags_str
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/research/'+tags_str

      }).then(response => {
      

        var new_soa = [];
        var id_cluster=[];
        for (const key in response.data.soa) {
          
          response.data.soa[key].id_research = val
          new_soa.push(response.data.soa);
          val ++;
          //get ID cluster from SOA
          if(!(id_cluster.includes(response.data.soa[key].cluster))){
            id_cluster.push(response.data.soa[key].cluster);
          }
          
        }

        
        //console.log(new_soa);
        var dic = {"nodes": response.data.soa};
        /* Legend */ 
        var namesColors = []
      
          console.log(id_cluster);
          
          var colorsClusters = ["#1B9E77", "#D95F02", "#7570B3","#E7298A","#E6AB02","#c90a0a","#0e3eaf","#60f2a4"];

          for(var i=0; i < id_cluster.length; i++){
           namesColors.push([colorsClusters[i], response.data.cluster[id_cluster[i]]]); 
            }
         
         this.clusterNames = namesColors;
      
      
      
        this.some_data = dic;
        
        //sauvegarde du contexte pour l'avoir un sous niveau plus bas
    
    //console.log(json);

   
      var config = {
            cluster: true,
            backgroundColor: "#4C4C4C",
            dataSource: this.some_data,
            nodeTypes: { "role": 
                ["10", "20", "30", "40","50","100","200"] 
            },
            nodeStyle: {
              "10": {
                "radius": 20
            },
            "20": {
                "radius": 30
            },
            "30": {
                "radius": 40
            },
            "40": {
                "radius": 50
            },
            "50": {
                "radius": 60
            },
            "100": {
                "radius": 70
            },
            "200": {
                "radius": 80
            }
        }
           };
      console.log(this.some_data);
      alchemy = new Alchemy(config);
      var self = this;
      alchemy.begin({dataSource: self.some_data,
      backgroundColor: "#4C4C4C",
      nodeCaption: function(node){
      return node.title;
    }, 
        nodeMouseOver: "title",
         nodeTypes: { "role": 
                ["10", "20", "30", "40","50","100","200"] 
            },
           nodeStyle: {
              "10": {
                "radius": 20
            },
            "20": {
                "radius": 30
            },
            "30": {
                "radius": 40
            },
            "40": {
                "radius": 50
            },
            "50": {
                "radius": 60
            },
            "100": {
                "radius": 70
            },
            "200": {
                "radius": 80
            }
        },
        cluster: true,
        
        //clusterColours: [clusters[0][0],clusters[1][0],clusters[2][0],clusters[3][0],clusters[4][0],clusters[5][0]],
        clusterColours: colorsClusters,
        clusterControl:false,
        nodeClick: function(node){console.log(node._properties.id_research); self.dialogShow(node._properties.id_research)}
        })
      })
    
    //Si empty affiche tous les états de l'art
    }else{
      this.bool_saved = false;
    var val = 1;
       axios({
        method: 'GET',
        //
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'
      }).then(response => {
        console.log(response.data);

        var new_soa = [];
        for (const key in response.data) {
          response.data[key].id_research = val
          
          new_soa.push(response.data);
          val ++;
        }
        //var new_soa = response.data["id_research"] = val;
        //console.log(new_soa);
        var dic = {"nodes": response.data};
      
      
      
        this.some_data = dic;
        //sauvegarde du contexte pour l'avoir un sous niveau plus bas
    
    //console.log(json);
      var config = {
            cluster: true,
            backgroundColor: "#4C4C4C",
            dataSource: this.some_data,
            nodeTypes: { "role": 
                ["10", "20", "30", "40","50","100","200"] 
            },
            nodeStyle: {
              "10": {
                "radius": 20
            },
            "20": {
                "radius": 30
            },
            "30": {
                "radius": 40
            },
            "40": {
                "radius": 50
            },
            "50": {
                "radius": 60
            },
            "100": {
                "radius": 70
            },
            "200": {
                "radius": 80
            }
        }
           };
           
      alchemy = new Alchemy(config);
      var self = this;
      alchemy.begin({dataSource: this.some_data,
      backgroundColor: "#4C4C4C",
      nodeCaption: function(node){
      return node.title;
    }, 
        nodeMouseOver: "title",
         nodeTypes: { "role": 
                ["10", "20", "30", "40","50","100","200"] 
            },
           nodeStyle: {
              "10": {
                "radius": 20
            },
            "20": {
                "radius": 30
            },
            "30": {
                "radius": 40
            },
            "40": {
                "radius": 50
            },
            "50": {
                "radius": 60
            },
            "100": {
                "radius": 70
            },
            "200": {
               "color": "#000067",
                "radius": 80
            }
        },
        cluster: true,
        
        //clusterColours: [clusters[0][0],clusters[1][0],clusters[2][0],clusters[3][0],clusters[4][0],clusters[5][0]],
        clusterColours: colorsClusters,
        nodeClick: function(node){console.log(node._properties.id_research); self.dialogShow(node._properties.id_research)}
        })
      })
    }

    
  }
}
</script>


<style scoped>
    .rounded {
        border-radius: 10px;
        padding: 20px;
        padding-top: 10px;
        height: 770px;
    }
    .rightspace{
      margin-left: 10px;
    }
    #alchemy {
      max-height: 85%;
      max-width : 100%;
      padding: 20px;
    }
    .save-divider {
        margin-right: 42px;
        margin-left: 45px;
        margin-top: 0px;
        margin-bottom: 0px;
        max-height: 1px;
        
    }
    .save-btn {
        margin-right: 33px;
        margin-top: 5px;
        margin-bottom: 5px;
    }
    .save-name {
        margin-right: 30px;
        margin-left: 30px
    }
    .cancel-btn {
        margin-left: 33px;
        margin-top: 5px;
        margin-bottom: 5px;
        text-decoration: none;
    }
</style>