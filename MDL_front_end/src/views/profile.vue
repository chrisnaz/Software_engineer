<template>
    <v-layout column mt-5 ml-5 mr-5 >
        <v-layout  align-start row>
            <v-flex>
                <h1 class="pink_custom--text"> My User Profile </h1>
            </v-flex>
        </v-layout>
        <v-layout align-start row>
            <!-- Name, First Name, Email, Password -->
            <v-flex xs4 mr-5>
                <v-layout fill-height column mt-5>
                    <v-flex>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4>
                                <h4 class="font-weight-regular" > Name : </h4>
                            </v-flex>
                            <v-flex  xs8>
                                <v-text-field :disabled="modif" hide-details v-model="name" :value="name" flat solo background-color="fields_grey"> </v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-3>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> First Name : </h4>
                            </v-flex>
                            <v-flex xs8 >
                                <v-text-field :disabled="modif" hide-details v-model="firstName" :value="firstName" flat solo background-color="fields_grey"> </v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-3> 
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Email : </h4>
                            </v-flex>
                            <v-flex  xs8 >
                                <v-text-field :disabled="modif" hide-details v-model="email" :value="email" flat solo background-color="fields_grey"> </v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                     <v-flex mt-3> 
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4>
                                <h4 class="font-weight-regular"> Password : </h4>
                            </v-flex>
                            <v-flex  xs8 >
                                <v-text-field :disabled="modif"
                                              hide-details
                                              v-model="password" 
                                              :value="password" 
                                              flat
                                              solo 
                                              background-color="fields_grey"
                                              :append-icon="show1 ? 'visibility' : 'visibility_off'"
                                              :type="show1 ? 'text' : 'password'"
                                              @click:append="show1 = !show1"
                                              color="pink_custom"
                                              > 
                                </v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
            <!-- Establishment, Status -->
            <v-flex xs4 ml-5 >
                <v-layout fill-height column mt-5>
                    <v-flex>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs5>
                                <h4 class="font-weight-regular"> Establishment : </h4>
                            </v-flex>
                            <v-flex  xs7>
                                <v-text-field :disabled="modif" hide-details v-model="establishment" :value="establishment" flat solo background-color="fields_grey"> </v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-2 mb-2 >
                        <v-layout align-start justify-space-between row>
                            <v-flex xs5>
                                <h4 class="font-weight-regular"> Status : </h4>
                            </v-flex>
                            <v-flex  xs7>
                                <v-menu full-width :disabled="modif">
                                    <v-btn block flat slot="activator" class="fields_grey">
                                        <span> {{status}} </span>
                                        <v-icon right> expand_more </v-icon>
                                    </v-btn>
                                    <v-list >
                                        <v-list-tile v-for="item in items" :key="item.title" router @click=" status = item.title" > 
                                            <v-list-tile-title class="pink_custom--text" v-model="statut"> {{ item.title}} </v-list-tile-title>
                                        </v-list-tile>
                                    </v-list>
                                </v-menu>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex  >
                        <v-layout align-start justify-space-between row>
                            <v-flex mt-2 xs4 style="height: 50px">
                                <!-- alignement par rapport à la colonne de gauche -->
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-4 v-if="modif== false"> 
                        <v-layout align-start justify-space-between row>
                            <v-flex xs6 >
                                <h4 class="font-weight-regular pink_custom--text"> Password Confirmation : </h4>
                            </v-flex>
                            <v-flex  xs6 >
                                <v-text-field :disabled="modif"
                                              
                                              v-model="password_conf" 
                                              :hint='confpasswordrules()'
                                              flat
                                              solo 
                                              background-color="fields_grey"
                                              :append-icon="show2 ? 'visibility' : 'visibility_off'"
                                              :type="show2 ? 'text' : 'password'"
                                              @click:append="show2 = !show2"
                                              color="pink_custom"
                                              > 
                                </v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex> 
            <!-- Avatar TODO: modif de l'avatar -->
            <v-flex ml-5 xs4>
                
                <v-layout justify-center column>
                    <v-flex>
                    <v-avatar fluid size="250">
                        <img :src="imageUrl" />
                    </v-avatar>
                    </v-flex>
                    <v-flex mt-2>
                        <v-btn to="/mySoa" flat round large class="pink_custom white--text"> 
                            <v-icon left class="white--text"> list_alt </v-icon>
                            States of the Art
                        </v-btn>
                        <div v-if="modif== false">
                            <v-btn class="pink_custom white--text"
                                @click="$refs.inputUpload.click()"
                                v-model='imageName'
                                flat 
                                round 
                                large 
                                >
                                    <v-icon left class="white--text"> collections </v-icon>
                                    Change picture 
                                </v-btn>
                            <input v-show="false" ref="inputUpload" accept="image/*" type="file" @change="onFilePicked" >    
                        </div>
                    </v-flex>
                    
                
                </v-layout>
            </v-flex>  
        </v-layout>
        <!-- Biography 
        <v-layout align-start justify-start row mt-5>
                <v-flex xs2>
                    <h4 class="font-weight-regular"> Biography : </h4>
                </v-flex>
                <v-flex xs8>
                    <v-textarea style="border-radius: 5px"
                                class="fields_grey"
                                outline
                                hide-details
                                color="pink_custom"
                                label="About you"
                                v-model="biography"
                                :value="biography"
                                auto-grow
                                :disabled="modif">
                    </v-textarea>
                </v-flex>
        </v-layout>-->
        <hr class="mt-5 mb-5">
        <v-layout align-end justify-end row mb-5>
            <v-flex xs2 mr-5>
                <v-btn outline flat round large to="/"> Back Home
                </v-btn>
            </v-flex>
            
            <v-flex xs2 v-if="modif== true">
                <v-btn @click="modif =!modif" flat round large class="pink_custom white--text"> Modify
                </v-btn>
            </v-flex>
            <v-flex xs2 v-if="modif== false">
                <v-btn :disabled='!isSaveComplete' @click="modif =!modif" flat round large outline class="pink_custom--text"> Save
                </v-btn>
            </v-flex>


        </v-layout>
    </v-layout>
    
    
</template>

<script>
import axios from 'axios'
export default {
    data (){
        return {
            name: 'Lard',
            firstName: 'Petit',
            email:'petit.lard@student.unamur.be',
            password: 'lelardonfume',
            password_conf: '',
            establishment: 'Unamur',
            status:'Researcher',
            biography: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ac aliquet odio. Integer sagittis lorem orci, eu fermentum erat ullamcorper vitae. Nunc non ultrices odio. Sed ac elit porttitor, condimentum magna ac, pharetra tellus. Proin tincidunt mattis ex id viverra. Curabitur sit amet ligula odio. Etiam ac volutpat nisl. Donec in sapien sit amet ipsum interdum efficitur sit amet vel mi. Phasellus hendrerit convallis risus, a sodales nisl porttitor imperdiet. Vestibulum ultricies consectetur rutrum. Sed dapibus ex in aliquam fringilla. Quisque cursus pretium ex eu consequat. Fusce venenatis vel metus in condimentum. Nunc porta eros non fermentum aliquet. Sed a facilisis nibh, ut posuere massa. Mauris metus enim, molestie at ultricies at, luctus in mauris. Ut id scelerisque magna. Donec a quam placerat, egestas nunc non, malesuada sapien.',
            modif: true,
            show1: false,
            show2: false,
            avatar: 'http://www.allodessin.com/tuts/51/dessin-de-cochon-mignon-de-dessin-anime.jpg',

            PageTitle: '',
            imageName: '',
			imageUrl: 'http://www.allodessin.com/tuts/51/dessin-de-cochon-mignon-de-dessin-anime.jpg',
            imageFile: '',

            items: [
                { title: 'Professor' },
                { title: 'Researcher' },
                { title: 'Student' },
                ]
        }

    },created(){
        let urlParams = new URLSearchParams(window.location.search);
        let id = urlParams.get('id');
        if (!(id == null)){
        
        axios({
        method: 'GET',
        // http://localhost:8080/Users/
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Users/'+id.toString()
      }).then(response => {
          this.firstName = response.data.firstname;
          this.name = response.data.lastname;
          this.establishment =response.data.establishment;
          this.email =response.data.email;
           this.status =response.data.statut;
      })
    
    }else{
        axios({
        method: 'GET',
        // http://localhost:8080/UsersName/
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()

        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/UsersName/'+localStorage.getItem('accessUsername').toString()

        }).then(response => {
          this.firstName = response.data.firstname;
          this.name = response.data.lastname;
          this.establishment =response.data.establishment;
          this.email =response.data.email;
          this.status =response.data.statut;
      })
    


    }
    }

    ,
    mounted: function() {
	},
	methods: {
        
        

        confpasswordrules() {
          return (this.password === this.password_conf) ? 'password confirmed' : 'password must match'
        } ,    

		pickFile () {
				this.$refs.image.click ()
		},
		onFilePicked (e) {
			const files = e.target.files
			if(files[0] !== undefined) {
				this.imageName = files[0].name
				if(this.imageName.lastIndexOf('.') <= 0) {
					return
				}
				const fr = new FileReader ()
				fr.readAsDataURL(files[0])
				fr.addEventListener('load', () => {
					this.imageUrl = fr.result
					this.imageFile = files[0] // this is an image file that can be sent to server...
				})
			} else {
				this.imageName = '';
				this.imageFile = '';
				this.imageUrl = '';
			}
		}
    },
    computed: {
       isSaveComplete () {
        return (this.password === this.password_conf);
      }
    }

    
}
</script>
