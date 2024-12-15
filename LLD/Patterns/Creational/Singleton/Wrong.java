package LLD.Patterns.Creational.Singleton;

// everything about singleton section referenced from - https://blog.algomaster.io/p/singleton-design-pattern

// Useful when need to only create one instance of class
// To do so , we make constructor private and provide a static method to get the instance
// Implementation : Following are ways to achieve this 
// 1. Lazy initialization
// 2. Thread safe singleton
// 3. Double-checked lock
// Pros
// Check this - https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2Ff47f0ad9-a144-493f-a78a-e5e99a1154c0_1952x928.png

// Cons
// Check this - https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2Ff47f0ad9-a144-493f-a78a-e5e99a1154c0_1952x928.png

// Note: I couldn't find a wrong way for singleton pattern , so there is no code here