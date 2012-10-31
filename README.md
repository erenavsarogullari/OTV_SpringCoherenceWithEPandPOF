How to distribute Spring Beans by using EntryProcessor and PortableObject features in Oracle Coherence

This article shows how to distribute Spring beans by using EntryProcessor and Portable Object Format(POF) features in Oracle Coherence.

Coherence supports a lock-free programming model through the EntryProcessor API. This feature improves system performance by reducing network access and performing an implicit low-level lock on the entries. This implicit low-level locking functionality is different than the explicit lock(key) provided by ConcurrentMap API.

Explicit locking, Transaction Framework API and Coherence Resource Adapter are other Coherence Transaction Options as Entry Processors. For detailed informations about Coherence Transaction Options, please look at the references section. In addition, Distributed Data Management in Oracle Coherence Article can be suggested for the Coherence Explicit locking implementation.

Portable Object Format(POF) is a platform-independent serialization format. It allows to encode equivalent Java, .NET and C++ objects into the identical sequence of bytes. POF is suggested for the system performance since Serialization and Deserialization performances of POF are better than the Standart Java Serialization(According to Coherence Reference document, in a simple test class with a String, a long, and three ints, (de)serialization was seven times faster than the Standart Java Serialization).

Coherence offers many kinds of cache types such as Distributed(or Partitioned), Replicated, Optimistic, Near, Local and Remote Cache. Distributed cache is defined as a collection of data that is distributed (or, partitioned) across any number of cluster nodes such that exactly one node in the cluster is responsible for each piece of data in the cache, and the responsibility is distributed (or, load-balanced) among the cluster nodes. Please note that distributed cache type has been used in this article. Since the other cache-types are not in the scope of this article, please look at the References section or Coherence Reference document. Their configurations are very similar to distributed cache configuration.

How to distribute Spring Beans by using Coherence Article covering Explicit locking � Java Standart Serialization is suggested to compare two different implementations(EntryProcessor � Portable Object Format(POF) and Explicit locking � Java Standart Serialization).

In this article, a new cluster called OTV has been created and a spring bean has been distributed by using a cache object called "user-cache". It has been distributed between two members of the cluster.

Let us look at implementation of AbsctractProcessor implementing EntryProcessor Interface and PortableObject Interface for Spring Beans� distribution between JVMs in a cluster.

Used Technologies :

JDK 1.6.0_31, Spring 3.1.1, Coherence 3.7.0, SolarisOS 5.10 and Maven 3.0.2