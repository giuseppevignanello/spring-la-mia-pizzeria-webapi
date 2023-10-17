<template>
  <div>
    <h1>Pizze</h1>
    <div>
      <div>
        <a href="#">
          <router-link :to="{ name: 'create' }">
            Create
          </router-link>
        </a>
      </div>
      <ul>
        <li v-for=" pizza  in  pizzas ">
          <p> {{ pizza.name }} </p>
          <p> {{ pizza.description }}</p>
          <p> $ {{ pizza.price }}</p>
          <div class="buttons">
            <button>Edit</button>
            <button @click="pizzaDelete(pizza.id)">Delete</button>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios';
const apiUrl = "http://localhost:8080/api/v1.0";
const pizzas = ref(null);

function pizzaDelete(id) {
  axios.delete(apiUrl + '/' + id)
    .then(response => {
      getAll();
    })
    .catch((error) => {
      console.error(error.message);
    })

}

function getAll() {
  axios.get(apiUrl)
    .then((response) => {
      pizzas.value = response.data;
    })
    .catch((error) => {
      console.error(error.message);
    })
}

onMounted(() => {
  getAll();
}
)
</script>

<style lang="scss" scoped></style>