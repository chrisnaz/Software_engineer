<template>
    <v-layout column mt-5 ml-5 mr-5 >
        <v-layout  align-start row>
            <v-flex>
                <h1 class="pink_custom--text"> User Profile </h1>
            </v-flex>
        </v-layout>
        <v-layout ml-2 align-start row>
            <!-- Name, First Name, Email, Password -->
            <v-flex xs4 mr-5>
                <v-layout fill-height column mt-5>
                    <v-flex>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Name : </h4>
                            </v-flex>
                            <v-flex xs8>
                                 <h4 class="font-weight-light font-italic pink_custom--text" > {{name}} </h4>
                                <!--
                                <v-text-field :disabled="modif" hide-details v-model="name" :value="name" flat solo background-color="fields_grey"> </v-text-field>
                            -->
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-3>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> First Name : </h4>
                            </v-flex>
                            <v-flex  xs8 >
                                <h4 class="font-weight-light font-italic pink_custom--text"> {{firstName}} </h4>
                                <!--
                                <v-text-field :disabled="modif" hide-details v-model="firstName" :value="firstName" flat solo background-color="fields_grey"> </v-text-field>
                            -->
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-3> 
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Email : </h4>
                            </v-flex>
                            <v-flex  xs8 >
                                <h4 class="font-weight-light font-italic pink_custom--text"> {{email}} </h4>
                                <!--
                                <v-text-field :disabled="modif" hide-details v-model="email" :value="email" flat solo background-color="fields_grey"> </v-text-field>
                            -->
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
                            <v-flex xs7 >
                                <h4 class="font-weight-light font-italic pink_custom--text"> {{establishment}} </h4>
                                <!--
                                <v-text-field :disabled="modif" hide-details v-model="establishment" :value="establishment" flat solo background-color="fields_grey"> </v-text-field>
                                -->
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs5>
                                <h4 class="font-weight-regular"> Status : </h4>
                            </v-flex>
                            <v-flex  xs7 >
                                <h4 class="font-weight-light font-italic pink_custom--text"> {{status}} </h4>
                                <!--
                                <v-text-field :disabled="modif" hide-details v-model="status" :value="status" flat solo background-color="fields_grey"> </v-text-field>
                            -->
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex> 
            <!-- Avatar -->
            <v-flex xs4>
                <v-layout align-center justify-center column>
                    <v-flex>
                    <v-avatar size="250">
                        <img :src="avatar">
                    </v-avatar>
                    </v-flex>
                    <v-flex mt-2>
                        <v-btn to="/userSoa" flat round large class="pink_custom white--text"> 
                            <v-icon left class="white--text"> list_alt </v-icon>
                            States of the Art
                        </v-btn>
                    </v-flex>
                </v-layout>
            </v-flex>  
        </v-layout>
        <!-- Biography -->
        <v-layout align-start justify-start row mt-5>
                <v-flex xs2>
                    <h4 class="font-weight-regular"> Biography : </h4>
                </v-flex>
                <v-flex xs8>
                    <div class="pa-3"  style="text-align:justify; border: 1px solid #9A2949; border-radius: 25px"> {{ biography }}
                    </div>
                    <!--
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
                    -->
                </v-flex>
        </v-layout>
        <hr class="mt-5 mb-5">
        <v-layout align-end justify-end row mb-5>
            <v-flex xs1 mr-5>
                <v-btn outline flat round large to="/"> Back Home
                </v-btn>
            </v-flex>
            
            


        </v-layout>
    </v-layout>
    
    
</template>

<script>
export default {
    data (){
        return {
            name: 'Name',
            firstName: 'First Name',
            email:'Email',
            
            establishment: 'Establishment',
            status:'Status',
            biography: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ac aliquet odio. Integer sagittis lorem orci, eu fermentum erat ullamcorper vitae. Nunc non ultrices odio. Sed ac elit porttitor, condimentum magna ac, pharetra tellus. Proin tincidunt mattis ex id viverra. Curabitur sit amet ligula odio. Etiam ac volutpat nisl. Donec in sapien sit amet ipsum interdum efficitur sit amet vel mi. Phasellus hendrerit convallis risus, a sodales nisl porttitor imperdiet. Vestibulum ultricies consectetur rutrum. Sed dapibus ex in aliquam fringilla. Quisque cursus pretium ex eu consequat. Fusce venenatis vel metus in condimentum. Nunc porta eros non fermentum aliquet. Sed a facilisis nibh, ut posuere massa. Mauris metus enim, molestie at ultricies at, luctus in mauris. Ut id scelerisque magna. Donec a quam placerat, egestas nunc non, malesuada sapien.',
            modif: true,
            show1: false,
            show2: false,
            avatar: 'https://images.ecosia.org/5FO25vCadd6ZwqLmAo7aDZ_wt2w=/0x390/smart/https%3A%2F%2Fwww.kirkleescollege.ac.uk%2Fwp-content%2Fuploads%2F2015%2F09%2Fdefault-avatar.png',

            items: [
                { title: 'Professor' },
                { title: 'Researcher' },
                { title: 'Student' },
                ]
        }
    }
    
}
</script>
