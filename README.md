# KMM Miam SDK

## Introduction

This SDK aims to facilitate the integration of Miam eCommerce to any grocery shopping mobile
application.

It implements a series of components that can be re-styled to your standards, and injected into your
app. Miam Components interact with each other and take care of the communication with Miam API.
Using this SDK, you should not need to communicate with Miam API directly from your app.

### 3 steps integration

The integration of the SDK into your app will take three steps:

1. **Initialization** : define the few mapping functions the SDK needs to interact with your app (
   push products to basket, retrieve the user unique id...)
2. **Components injection** : fill your app with Miam components wherever relevant (recipe cards in
   search results grids, recipes catalog on a dedicated page...)
3. **Styling** : apply your own stylesheets, globally at the SDK level, and specifically for each
   component

For more information see documentation