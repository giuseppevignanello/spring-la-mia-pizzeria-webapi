<template>
    <div>
        <form @submit.prevent="storePizza">
            <label for="name">Name</label>
            <br>
            <input type="text" name="name" v-model="newPizza.name">
            <br>
            <label for="description">Description</label>
            <br>
            <input type="text" name="description" v-model="newPizza.description">
            <br>
            <label for="photo">Photo</label>
            <br>
            <input type="text" name="photo" v-model="newPizza.photo">
            <br>
            <label for="price">Price</label>
            <br>
            <input type="number" name="price" v-model="newPizza.price">
            <br><br>
            <input type="submit" value="CREATE">
            <button @click="clear">CANCEL</button>
        </form>
    </div>
</template>

<script setup>

import { onMounted, ref } from 'vue';
import axios from 'axios';
const apiUrl = "http://localhost:8080/api/v1.0";
const emptyPizza = {
    name: null,
    description: null,
    photo: null,
    price: null
};
const newPizza = ref({ ...emptyPizza });

function clear() {
    newPizza.value = ref({ ...emptyPizza });
}
function storePizza() {


    axios.post(apiUrl, newPizza.value)
        .then(response => {

            const data = response.data;

            clear();
        })
        .catch(err => console.log(err));
}
</script>

<style lang="scss" scoped></style>