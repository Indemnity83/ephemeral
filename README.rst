##################################################################
Ephemeral: an opinionated client side server manager for Minecraft
##################################################################

.. class:: no-web

    Ephemeral is an **opinionated client side server manager for Minecraft**.
    Its goal is to make it affordable for individuals and small groups to run
    a Minecraft server in the cloud. It provides an interface and pre-configuration
    to allow the startup and shutdown of Amazon EC2 (preferably spot instances)
    from within the game. Meaning you don't have to pay for a server when you're
    not playing!

.. contents::

.. section-numbering::

======================
AWS Billing Disclaimer
======================

    Amazon Web Services cost estimataing can be complicated, Make sure you read
    all the documentation here and for AWS so you understand how Amazon Web Services
    works before you attempt to use this mod. I have done everything I can to make
    any expected charges known to you, but ultimately I cannot be held responsible
    for any unexpected charges on your account.

    That out of the way, here's what you can expect in general terms. There are
    multiple components in play together to make this work smoothly.

    1. EC2 Spot Instances (the main compute power).
    2. ElasticIP Addresses (so you always have the same IP).
    3. EC2 Block Storage (for persisting your Mincraft game between sessions).

    The EC2 instance is the piece that can be started up and shutdown on demand with
    this mod, and should constitute the majority of the cost. But in order to make
    the process seamless, the ElasticIP and EC2 Block Storage must persist when the
    main computer is terminated. This isn't free, however its very inexpensive.

==================
EC2 Spot Instances
==================

    The purpose of this mod is to reduce cost, and take advantage of a type of cloud
    server that Amazon Web Services offers called a spot instance. These are usually
    very cheap servers (in the area of a penny per hour for something that
    can run a Minecraft server) however they essentially require automated deployment
    to be used successfully.

    There are some catches with Spot Instances. You should ready the description from
    Amazon, but in summary you are bidding on a server, not outright paying for it. You
    set a maximum you're willing to pay for the instance, but if there are no spot
    instances available and somebody is willing to pay more then you, your instance
    may be shutdown (with a 2 min warning) so it can be provisioned to the higher bidder.

================
How Will It Work
================

    At a high level, each time you startup your cloud server, a new computer is created
    and your public IP address is associated to that machine. The machine boots from
    a pre-configured hard drive that includes all the system tools to run Minecraft, it
    also attaches your EC2 Block Storage drive as a second disk and mounts it to
    /minecraft. Once the server is booted, a service starts, and maintains the running
    of Minecraft from the /minecraft share, so all data (world files, mods, configs etc)
    never leave your Block Storage disk.

    When you're done with the server, you terminate it (or you set default timeouts for
    automatic termination). On termination the server shuts down, disconnects the public
    IP address, and the Block Storage drive (with all your data) and then is destroyed.

================
Work In Progress
================

    This project is a very early work in progress, as such its not intended
    for use on a live server. Large portions of it are incomplete.