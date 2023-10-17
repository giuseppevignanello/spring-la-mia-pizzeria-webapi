<template>
    <div>
        <form @submit.prevent="updatePizza(pizza.id)">
            <label for="name">Name</label>
            <br>
            <input type="text" name="name" v-model="editedPizza.name">
            <br>
            <label for="description">Description</label>
            <br>
            <input type="text" name="description" v-model="editedPizza.description">
            <br>
            <label for="photo">Photo</label>
            <br>
            <input type="text" name="photo" v-model="editedPizza.photo">
            <br>
            <label for="price">Price</label>
            <br>
            <input type="number" name="price" v-model="editedPizza.price">
            <br><br>
            <input type="submit" value="EDIT">
            <button @click="clear">CANCEL</button>
        </form>
    </div>
</template>

<script setup>

import { onMounted, ref } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
const id = route.params.id;
const apiUrl = "http://localhost:8080/api/v1.0";
const emptyPizza = {
    name: null,
    description: null,
    photo: null,
    price: null
};
const editedPizza = ref({ ...emptyPizza });

function clear() {
    editedPizza.value = ref({ ...emptyPizza });
}
function updatePizza(id) {
    axios.put(`${apiUrl}/${id}`, editedPizza.value)
        .then(response => {

            const data = response.data;

            clear();
        })
        .catch(err => console.log(err));
}

onMounted(async () => {
    try {
        const response = await axios.get(`${apiUrl}/${id}`);
        editedPizza.value = response.data;
    } catch (error) {
        console.error(error.message);
    }
});


</script>

<style lang="scss" scoped></style>